package com.hmdp.service.impl;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.Result;
import com.hmdp.entity.RedisData;
import com.hmdp.entity.Shop;
import com.hmdp.mapper.ShopMapper;
import com.hmdp.service.IShopService;
import com.hmdp.utils.CacheClient;
import com.hmdp.utils.SystemConstants;
import jakarta.annotation.Resource;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.domain.geo.GeoReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private CacheClient cacheClient;

    @Override
    public Result queryById(Long id) {
        //缓存穿透
//        Shop shop = queryWithPassThrough(id);
        //Shop shop = cacheClient.queryWithPassThrough(CACHE_SHOP_KEY, id, Shop.class, this::getById, CACHE_SHOP_TTL, TimeUnit.MINUTES);
        //互斥锁解决缓存击穿（其中还包含了缓存穿透）
//        Shop shop = queryWithMutex(id);
        //逻辑过期解决缓存击穿
//        Shop shop = queryWithLogicalExpire(id);
        Shop shop = cacheClient.queryWithLogicalExpire(CACHE_SHOP_KEY, id, Shop.class, this::getById, 20L, TimeUnit.SECONDS);
        if (shop == null) {
            return Result.fail("商铺不存在");
        }
        return Result.ok(shop);
    }

    //设置线程池
    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

    /**
     * 逻辑过期解决缓存击穿
     *
     * @param id 商铺id
     * @return 商铺信息
     */
    public Shop queryWithLogicalExpire(Long id) {
        Shop shop = checkRedisTime(id);
        // 6 重建缓存
        // 获取互斥锁
        String lockKey = LOCK_SHOP_KEY + id;
        // 判断互斥锁是否获取成功
        boolean isLock = tryLock(lockKey);
        if (isLock) {
            //再次redis缓存是否过期
            shop = checkRedisTime(id);
            // 成功 开启独立线程 实现缓存重建 这个时候不会等待重建完成 而是返回旧信息
            CACHE_REBUILD_EXECUTOR.submit(() -> {
                try {
                    this.saveShop2Redis(id, 20L);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    // 释放锁
                    unlock(lockKey);
                }
            });
        }
        //返回旧信息
        return shop;
    }

    private Shop checkRedisTime(Long id) {
        // 1 从redis查询商铺信息缓存
        String shopJson = stringRedisTemplate.opsForValue().get(CACHE_SHOP_KEY + id);
        // 2 判断是否存在
        if (StrUtil.isBlank(shopJson)) {
            //3 不存在 直接返回null
            return null;
        }
        /**
         * 4 命中 需要把json反序列化为对象
         * 5 判断是否逻辑过期
         *      逻辑未过期 直接返回商铺信息
         *      逻辑过期 需要重建缓存
         * 6 重建缓存
         *      获取互斥锁
         *      判断互斥锁是否获取成功
         *      成功 开启独立线程 实现缓存重建 这个时候不会等待重建完成 而是返回旧信息
         *      失败 返回旧信息
         */
        // 4 命中 需要把json反序列化为对象
        RedisData redisData = JSONUtil.toBean(shopJson, RedisData.class);
        // 这里进行了类型强转JSONObject 在进行类型转化
        Shop shop = JSONUtil.toBean((JSONObject) redisData.getData(), Shop.class);
        LocalDateTime expireTime = redisData.getExpireTime();
        // 5 判断是否逻辑过期
        if (expireTime.isAfter(LocalDateTime.now())) {
            // 逻辑未过期 直接返回商铺信息
            return shop;
        }
        return shop;
    }

    /**
     * 互斥锁解决缓存击穿（其中还包含了缓存穿透）
     *
     * @param id 商铺id
     * @return 商铺信息
     */
    public Shop queryWithMutex(Long id) {
        // 1 从redis查询商铺信息缓存
        String shopJson = stringRedisTemplate.opsForValue().get(CACHE_SHOP_KEY + id);
        // 2 判断是否存在
        if (StrUtil.isNotBlank(shopJson)) {
            //3 存在 将json转化为商铺对象 并直接返回
            return JSONUtil.toBean(shopJson, Shop.class);
        }

        //判断命中的舒是否是空值 如果不判断就又会去查询数据库 导致缓存穿透
        if (shopJson != null) {
            return null;
        }
        /**
         * 实现缓存重建
         * 1 获取互斥锁
         * 2 判断是否获取成功
         * 3 失败 则休眠重试
         * 4 成功 根据id查询数据库
         */
        //获取互斥锁
        String lockKey = LOCK_SHOP_KEY + id;
        Shop shop = null;
        try {
            boolean isLock = tryLock(lockKey);
            if (!isLock) {
                Thread.sleep(50);
                // 休眠 使用递归重试
                return queryWithMutex(id);
            }
            /**
             * 再次判断是否存在
             */
            // 1 从redis查询商铺信息缓存
            shopJson = stringRedisTemplate.opsForValue().get(CACHE_SHOP_KEY + id);
            // 2 判断是否存在
            if (StrUtil.isNotBlank(shopJson)) {
                //3 存在 将json转化为商铺对象 并直接返回
                return JSONUtil.toBean(shopJson, Shop.class);
            }

            shop = getById(id);
            // 5 判断商铺是否存在
            if (shop == null) {
                // 6 不存在 返回商铺不存在
                // 6 不存在 将空值写入redis缓存 并返回信息 防止缓存穿透
                stringRedisTemplate.opsForValue().set(CACHE_SHOP_KEY + id, "", CACHE_NULL_TTL, TimeUnit.MINUTES);
                return null;
            }
            // 7 存在 写入redis缓存 并返回信息
            stringRedisTemplate.opsForValue().set(CACHE_SHOP_KEY + id, JSONUtil.toJsonStr(shop), CACHE_SHOP_TTL, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放锁
            unlock(lockKey);
        }

        return shop;
    }


    /**
     * 将缓存穿透封装 留着备用
     *
     * @param id 商铺id
     * @return 商铺信息
     */
    public Shop queryWithPassThrough(Long id) {
        // 1 从redis查询商铺信息缓存
        String shopJson = stringRedisTemplate.opsForValue().get(CACHE_SHOP_KEY + id);
        // 2 判断是否存在
        if (StrUtil.isNotBlank(shopJson)) {
            //3 存在 将json转化为商铺对象 并直接返回
//            Shop shop = JSONUtil.toBean(shopJson, Shop.class);
//            return Result.ok(shop);
            return JSONUtil.toBean(shopJson, Shop.class);
        }

        //判断命中的舒是否是空值 如果不判断就又会去查询数据库 导致缓存穿透
        if (shopJson != null) {
//            return Result.fail("商铺信息不存在");
            return null;
        }

        // 4 不存在 根据id查询数据库
        Shop shop = getById(id);
        // 5 判断商铺是否存在
        if (shop == null) {
            // 6 不存在 返回商铺不存在
            // 6 不存在 将空值写入redis缓存 并返回信息 防止缓存穿透
            stringRedisTemplate.opsForValue().set(CACHE_SHOP_KEY + id, "", CACHE_NULL_TTL, TimeUnit.MINUTES);
//            return Result.fail("商铺不存在");
            return null;
        }
        // 7 存在 写入redis缓存 并返回信息
        stringRedisTemplate.opsForValue().set(CACHE_SHOP_KEY + id, JSONUtil.toJsonStr(shop), CACHE_SHOP_TTL, TimeUnit.MINUTES);
        return shop;
    }

    @Override
    @Transactional
    public Result update(Shop shop) {
        Long id = shop.getId();
        if (id == null) {
            Result.fail("商铺id不能为空");
        }
        // 1 更新数据库
        updateById(shop);
        // 2 删除缓存
        stringRedisTemplate.delete(CACHE_SHOP_KEY + id);
        // 3 返回结果
        return Result.ok();
    }

    @Override
    public Result queryShopByType(Integer typeId, Integer current, Double x, Double y) {
        //TODO
        // 1.判断是否需要根据坐标查询
        if (x == null || y == null) {
            // 不需要坐标查询，按数据库查询
            Page<Shop> page = query()
                    .eq("type_id", typeId)
                    .page(new Page<>(current, SystemConstants.DEFAULT_PAGE_SIZE));
            // 返回数据
            return Result.ok(page.getRecords());
        }

        // 2.计算分页参数
        int from = (current - 1) * SystemConstants.DEFAULT_PAGE_SIZE;
        int end = current * SystemConstants.DEFAULT_PAGE_SIZE;

        // 3.查询redis、按照距离排序、分页。结果：shopId、distance
        String key = SHOP_GEO_KEY + typeId;
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = stringRedisTemplate.opsForGeo() // GEOSEARCH key BYLONLAT x y BYRADIUS 10 WITHDISTANCE
                .search(
                        key,
                        GeoReference.fromCoordinate(x, y),
                        new Distance(5000),
                        RedisGeoCommands.GeoSearchCommandArgs.newGeoSearchArgs().includeDistance().limit(end)
                );
        // 4.解析出id
        if (results == null) {
            return Result.ok(Collections.emptyList());
        }
        List<GeoResult<RedisGeoCommands.GeoLocation<String>>> list = results.getContent();
        if (list.size() <= from) {
            // 没有下一页了，结束
            return Result.ok(Collections.emptyList());
        }
        // 4.1.截取 from ~ end的部分
        List<Long> ids = new ArrayList<>(list.size());
        Map<String, Distance> distanceMap = new HashMap<>(list.size());
        list.stream().skip(from).forEach(result -> {
            // 4.2.获取店铺id
            String shopIdStr = result.getContent().getName();
            ids.add(Long.valueOf(shopIdStr));
            // 4.3.获取距离
            Distance distance = result.getDistance();
            distanceMap.put(shopIdStr, distance);
        });
        // 5.根据id查询Shop
        String idStr = StrUtil.join(",", ids);
        List<Shop> shops = query().in("id", ids).last("ORDER BY FIELD(id," + idStr + ")").list();
        for (Shop shop : shops) {
            shop.setDistance(distanceMap.get(shop.getId().toString()).getValue());
        }
        // 6.返回
        return Result.ok(shops);
    }
    /**
     * 缓存击穿
     *这里采用了redis的setnx 如果key没有被创建 则就是创建key 获取到锁
     * 如果key已经被创建 setnx就不会创建key 则就是获取不到锁
     * setnx 就是判断key是否存在 如果存在就不会创建key 如果不存在就创建key
     */

    /**
     * 给线程加上互传锁 防止缓存击穿
     *
     * @param key 锁的key
     * @return 是否获取到锁
     */
    private boolean tryLock(String key) {
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", 10, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(flag);
    }

    /**
     * 释放锁 执行完查询数据库并写入缓存 释放锁
     *
     * @param key 锁的key
     */
    private void unlock(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 缓存重建
     * 1 查询数据库
     * 2 封装逻辑过期时间
     * 3 将数据写入redis
     *
     * @param id            商铺id
     * @param expireSeconds 过期时间
     */

    public void saveShop2Redis(Long id, Long expireSeconds) {
        //1 查询店铺信息
        Shop shop = getById(id);
        //2 封装逻辑过期时间
        RedisData redisData = new RedisData();
        redisData.setData(shop);
        //3 设置过期时间 LocalDateTime.now().plusSeconds(expireSeconds) 表示当前时间加上过期时间
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(expireSeconds));
        //3 将数据写入redis
        stringRedisTemplate.opsForValue().set(CACHE_SHOP_KEY + id, JSONUtil.toJsonStr(redisData));
    }
}
