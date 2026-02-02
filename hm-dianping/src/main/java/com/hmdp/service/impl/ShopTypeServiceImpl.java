package com.hmdp.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Result queryAll() {
        String type = stringRedisTemplate.opsForValue().get("type");
        if (StrUtil.isNotBlank(type)) {
            List<ShopType> shopTypes = JSONUtil.toList(type, ShopType.class);
            return Result.ok(shopTypes);
        }
        List<ShopType> shopTypes = getBaseMapper().selectList(null);
        stringRedisTemplate.opsForValue().set("type", JSONUtil.toJsonStr(shopTypes));
        return Result.ok(shopTypes);
    }
}
