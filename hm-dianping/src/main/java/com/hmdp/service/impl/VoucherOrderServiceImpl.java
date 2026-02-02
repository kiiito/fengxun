package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.VoucherOrder;
import com.hmdp.mapper.VoucherOrderMapper;
import com.hmdp.service.ISeckillVoucherService;
import com.hmdp.service.IVoucherOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.RedisIdWorker;
import com.hmdp.utils.UserHolder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Slf4j
@Service
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder> implements IVoucherOrderService {

    @Resource
    private ISeckillVoucherService  seckillVoucherService;
    @Resource
    private RedisIdWorker  redisIdWorker;
    @Resource
    private StringRedisTemplate  stringRedisTemplate;

    @Resource
    private RedissonClient  redissonClient;
    /**
     *这里进行脚本的初始化
     */
    private static final DefaultRedisScript<Long> SECKILL_SCRIPT;
    static{
        // 1 创建一个脚本对象
        SECKILL_SCRIPT = new DefaultRedisScript<>();
        // 2 设置脚本的路径
        SECKILL_SCRIPT.setLocation(new ClassPathResource("seckill.lua"));
        // 3 设置返回值类型
        SECKILL_SCRIPT.setResultType(Long.class);
    }

    /**
     * 这里创建一个代理对象 用来处理下单任务
     */
    private  IVoucherOrderService proxy;
    /**
     * 这里创建一个线程池 用来处理下单任务
     */
    private static final ExecutorService SECKILL_ORDER_EXECUTOR = Executors.newSingleThreadExecutor();
    /**
     * 初始化方法（Spring启动时执行）
     * 启动订单处理线程
     */
    @PostConstruct
    private void init() {
        SECKILL_ORDER_EXECUTOR.submit(new VoucherOderHandler());
    }

   String queueName = "stream.orders";

    /**
     * 这里创建一个线程 用来处理下单任务
     */
    private class VoucherOderHandler implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    // 1 获取消息队列中的订单信息 XREADGROUP GROUP g1 c1 COUNT 1 BLOCK 2000 STREAMS stream.orders >
                    List<MapRecord<String, Object, Object>> list = stringRedisTemplate.opsForStream().read(
                            Consumer.from("g1", "c1"),
                            StreamReadOptions.empty().count(1).block(Duration.ofSeconds(2)),
                            StreamOffset.create(queueName, ReadOffset.lastConsumed())
                    );
                    // 2 判断消息是否获取成功
                    if (list == null || list.isEmpty()) {
                        //如果获取失败 说明没有消息 继续下一次循环
                        continue;
                    }
                    //解析消息中的订单消息
                    MapRecord<String, Object, Object> record = list.get(0);
                    Map<Object, Object> values = record.getValue();
                    VoucherOrder voucherOrder = BeanUtil.fillBeanWithMap(values, new VoucherOrder(), true);
                    //如果获取成功 可以下单
                    handleVoucherOrder(voucherOrder);
                    //ACK确认 SACK stream.orders g1 id
                    stringRedisTemplate.opsForStream().acknowledge(queueName,"id",record.getId());
                } catch (Exception e) {
//                    throw new RuntimeException(e);
                    log.error("处理订单异常", e);
                    handlePendList();
                }
            }
        }

        private void handlePendList() {
            while (true) {
                try {
                    // 1 获取消息队列中的订单信息 XREADGROUP GROUP g1 c1 COUNT 1 BLOCK 2000 STREAMS stream.orders 0
                    List<MapRecord<String, Object, Object>> list = stringRedisTemplate.opsForStream().read(
                            Consumer.from("g1", "c1"),
                            StreamReadOptions.empty().count(1).block(Duration.ofSeconds(2)),
                            StreamOffset.create(queueName, ReadOffset.from("0"))
                    );
                    // 2 判断消息是否获取成功
                    if (list == null || list.isEmpty()) {
                        //如果获取失败 说明pending-list没有消息 继续下一次循环
                        break;
                    }
                    //解析消息中的订单消息
                    MapRecord<String, Object, Object> record = list.get(0);
                    Map<Object, Object> values = record.getValue();
                    VoucherOrder voucherOrder = BeanUtil.fillBeanWithMap(values, new VoucherOrder(), true);
                    //如果获取成功 可以下单
                    handleVoucherOrder(voucherOrder);
                    //ACK确认 SACK stream.orders g1 id
                    stringRedisTemplate.opsForStream().acknowledge(queueName,"id",record.getId());
                } catch (Exception e) {
//                    throw new RuntimeException(e);
                    log.error("处理pending-list异常", e);
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }

//    /**
//     * 这里创建一个阻塞队列
//     * 什么是阻塞队列
//     * 阻塞队列是一种线程安全的队列，当队列为空时获取操作会被阻塞，当队列满时插入操作会被阻塞，直到满足操作条件。
//     */
//    private BlockingQueue<VoucherOrder> orderTasks = new ArrayBlockingQueue<>(1024 * 1024);
//
//    /**
//     * 这里创建一个线程 用来处理下单任务
//     */
//    private class VoucherOderHandler implements Runnable {
//        @Override
//        public void run() {
//            while (true) {
//                try {
//                    // 1 获取队列中的订单信息
//                    VoucherOrder voucherOrder = orderTasks.take();
//                    // 2 创建订单
//                    handleVoucherOrder(voucherOrder);
//                } catch (Exception e) {
////                    throw new RuntimeException(e);
//                    log.error("处理订单异常", e);
//                }
//            }
//        }
//    }

    /**
     * 创建订单
     * @param voucherOrder 订单信息
     */
    private void handleVoucherOrder(VoucherOrder voucherOrder) {
        // 获取用户
        Long userId = voucherOrder.getUserId();
        //创建锁对象
        RLock lock = redissonClient.getLock("lock:order:" + userId);
        // 获取锁
        //选择无参 默认是不等待 超时时间为30s
        boolean isLock = lock.tryLock();
        if (!isLock) {
            //获取锁失败 返回错误或重试
            log.error("不允许重复下单");
            return;
        }
        try {
            //获取代理对象（事务）
            proxy.createVoucherOrder(voucherOrder);
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    @Override
    public Result seckillVoucher(Long voucherId) {
        //获取用户id
        Long userId = UserHolder.getUser().getId();
        // 2.4订单id
        long orderId = redisIdWorker.nextId("order");
        // 1 执行lua脚本
        Long result = stringRedisTemplate.execute(
                SECKILL_SCRIPT,
                Collections.emptyList(),
                voucherId.toString(), userId.toString(),
                String.valueOf(orderId)
        );
        // 2 判断结果是否为0
        int r = result.intValue();
        if (r != 0){
            // 2.1 不为0 代表没有购买资格（已经购买过了） 或者没有库存
            return Result.fail(r == 1 ? "库存不足" : "不允许重复下单");
        }
        // 2.8 获取代理对象
        proxy = (IVoucherOrderService) AopContext.currentProxy();
        // 3 返回订单id
        return Result.ok(orderId);

    }
//    @Override
//    public Result seckillVoucher(Long voucherId) {
//        //获取用户id
//        Long userId = UserHolder.getUser().getId();
//
//        // 1 执行lua脚本
//        Long result = stringRedisTemplate.execute(
//                SECKILL_SCRIPT,
//                Collections.emptyList(),
//                voucherId.toString(), userId.toString()
//        );
//        // 2 判断结果是否为0
//        int r = result.intValue();
//        if (r != 0){
//                // 2.1 不为0 代表没有购买资格（已经购买过了） 或者没有库存
//                return Result.fail(r == 1 ? "库存不足" : "不允许重复下单");
//            }
//
//        // 2.2 为0 代表可以购买 把下单信息保存到阻塞队列
//        // 2.3 创建订单
//        VoucherOrder voucherOrder = new VoucherOrder();
//        // 2.4订单id
//        long orderId = redisIdWorker.nextId("order");
//        voucherOrder.setId(orderId);
//        // 2.5用户id
//        voucherOrder.setUserId(userId);
//        // 2.6优惠券id
//        voucherOrder.setVoucherId(voucherId);
//        //2.7 放入阻塞队列
//        orderTasks.add(voucherOrder);
//        // 2.8 获取代理对象
//        proxy = (IVoucherOrderService) AopContext.currentProxy();
//        // 3 返回订单id
//        return Result.ok(orderId);
//
//    }

    @Transactional
    public void createVoucherOrder(VoucherOrder voucherOrder){
        //5 一人一单
        // 用户id
        Long userId = voucherOrder.getUserId();
        Long count = query().eq("user_id", userId).eq("voucher_id", voucherOrder.getVoucherId()).count();
        if (count > 0) {
            //用户已经购买了
            log.error("用户已经购买过了");
        }
        // 6 库存充足 扣减库存
        //.eq("stock", voucher.getStock())添加乐观锁 通过判断库存值是否等于我查询时的值来判断是否需要更新
        //.gt("stock", 0) 添加乐观锁 通过判断库存值是否大于0来判断是否需要更新
        boolean success = seckillVoucherService.update()
                .setSql("stock = stock - 1")
                .eq("voucher_id", voucherOrder.getVoucherId())
//                .eq("stock", voucher.getStock())
                .gt("stock", 0)
                .update();
        if (!success) {
            // 5 库存不足 返回异常信息
            log.error("库存不足");
            return;
        }
        // 7 创建订单
        save(voucherOrder);

    }

//    @Override
//    public Result seckillVoucher(Long voucherId) {
//        // 1 查询优惠劵
//        SeckillVoucher voucher = seckillVoucherService.getById(voucherId);
//        // 2 判断秒杀是否开始 是否结束
//        if (voucher.getBeginTime().isAfter(LocalDateTime.now())) {
//            // 3  直接返回异常信息
//            return Result.fail("秒杀未开始");
//        }
//        if (voucher.getEndTime().isBefore(LocalDateTime.now())) {
//            // 3  直接返回异常信息
//            return Result.fail("秒杀已结束");
//        }
//        // 4  判断库存是否充足
//        if (voucher.getStock() < 1) {
//            //  库存不足 返回异常信息
//            return Result.fail("库存不足");
//        }
//        Long userId = UserHolder.getUser().getId();
//
//        //创建锁对象
////        SimpleRedisLock lock = new SimpleRedisLock(stringRedisTemplate, "order:" + userId);
//        RLock lock = redissonClient.getLock("lock:order:" + userId);
//        // 获取锁
//        //选择无参 默认是不等待 超时时间为30s
//        boolean isLock = lock.tryLock();
//        if (!isLock) {
//            return Result.fail("不允许重复下单");
//        }
//
//        try {
//            //获取代理对象（事务）
//            IVoucherOrderService proxy = (IVoucherOrderService) AopContext.currentProxy();
//            return proxy.createVoucherOrder(voucherId);
//        } finally {
//            //释放锁
//            lock.unlock();
//        }
//
//    }
//    @Transactional
//    public Result createVoucherOrder(Long voucherId){
//        //5 一人一单
//        // 用户id
//        Long userId = UserHolder.getUser().getId();
//        Long count = query().eq("user_id", userId).eq("voucher_id", voucherId).count();
//        if (count > 0) {
//            //用户已经购买了
//            return Result.fail("用户已经购买过了");
//        }
//        // 6 库存充足 扣减库存
//        //.eq("stock", voucher.getStock())添加乐观锁 通过判断库存值是否等于我查询时的值来判断是否需要更新
//        //.gt("stock", 0) 添加乐观锁 通过判断库存值是否大于0来判断是否需要更新
//        boolean success = seckillVoucherService.update()
//                .setSql("stock = stock - 1")
//                .eq("voucher_id", voucherId)
////                .eq("stock", voucher.getStock())
//                .gt("stock", 0)
//                .update();
//        if (!success) {
//            // 5 库存不足 返回异常信息
//            return Result.fail("库存不足");
//        }
//        // 7 创建订单
//        VoucherOrder voucherOrder = new VoucherOrder();
//        // 订单id
//        long orderId = redisIdWorker.nextId("order");
//        voucherOrder.setId(orderId);
//        voucherOrder.setUserId(userId);
//        // 优惠券id
//        voucherOrder.setVoucherId(voucherId);
//        save(voucherOrder);
//        // 8 返回订单信息
//        return Result.ok(orderId);
//    }
}
