package com.itxiaoyang.service;

import com.itxiaoyang.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();//查询所有部门信息

    void deleteById(Integer id);//根据id删除部门

    void add(Dept dept);//添加部门信息

    Dept getById(Integer id);//根据id查询部门

    void update(Dept dept);//修改部门信息
}
