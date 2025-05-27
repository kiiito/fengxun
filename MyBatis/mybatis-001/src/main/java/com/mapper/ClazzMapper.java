package com.mapper;

import com.bean.Clazz;

public interface ClazzMapper {
    Clazz selectByIdStep2(Integer cid);
    Clazz selectByCollection(Integer cid);
    Clazz selectByStep1(Integer cid);
}
