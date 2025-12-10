package com.itxiaoyang.service;

import com.itxiaoyang.pojo.Emp;
import com.itxiaoyang.pojo.EmpQueryParam;
import com.itxiaoyang.pojo.LoginInfo;
import com.itxiaoyang.pojo.PageResult;

import java.util.List;

public interface EmpService {

    //条件分页查询
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    //新增员工
    void save(Emp emp);

    //批量删除员工信息
    void delete(List<Integer> ids);

    //根据id查询员工信息
    Emp getInfo(Integer id);

    //修改员工信息
    void update(Emp emp);

    //查询所有员工信息
    List<Emp> allList();

    //员工登录
    LoginInfo login(Emp emp);


    /**
     * 分页查询
     * @param page 页码
     * @param pageSize 每页展示的记录数
     * @return
     */
//    PageResult<Emp> page(Integer page, Integer pageSize,String name, Integer gender,LocalDate begin, LocalDate end);

}
