package com.hc.yupao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Tag;
import generator.mapper.TagMapper;
import generator.service.TagService;
import org.springframework.stereotype.Service;

/**
* @author 风寻
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2025-08-23 12:47:07
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




