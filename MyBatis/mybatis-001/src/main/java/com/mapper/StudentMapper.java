package com.mapper;

import com.bean.Student;

import java.util.List;

public interface StudentMapper {
    Student selectById(Integer id);
    Student selectByIdAssociation(Integer id);

    Student selectByIdStep1(Integer sid);
    List<Student> selectByCidStep2(Integer cid);
}
