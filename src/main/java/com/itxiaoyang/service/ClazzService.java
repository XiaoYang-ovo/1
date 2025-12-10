package com.itxiaoyang.service;

import com.itxiaoyang.controller.ClazzController;
import com.itxiaoyang.pojo.Clazz;
import com.itxiaoyang.pojo.ClazzQueryParam;
import com.itxiaoyang.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);//分页查询班级

    void delete(Integer id);//删除班级

    void save(Clazz clazz);//新增班级

    Clazz getInfo(Integer id);//根据id查询班级信息

    void update(Clazz clazz);//修改班级信息

    List<Clazz> allList();//查询所有班级信息
}
