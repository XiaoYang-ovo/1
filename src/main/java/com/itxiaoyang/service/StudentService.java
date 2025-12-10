package com.itxiaoyang.service;

import com.itxiaoyang.pojo.PageResult;
import com.itxiaoyang.pojo.Student;
import com.itxiaoyang.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PageResult<Student> page(StudentQueryParam studentQueryParam);//分页查询学员

    void delete(List<Integer> ids);//批量删除学员

    void save(Student student);//添加学员

    Student getInfo(Integer id);//根据id查询学员信息

    void update(Student student);//修改学员信息
}
