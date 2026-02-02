package com.hucong.springbootcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hucong.springbootcloud.domain.User;
import org.apache.ibatis.annotations.Mapper;


/**
* @author 风寻
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2025-11-04 11:37:42
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




