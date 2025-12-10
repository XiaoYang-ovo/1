package com.itxiaoyang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itxiaoyang.mapper.EmpExprMapper;
import com.itxiaoyang.mapper.EmpMapper;
import com.itxiaoyang.pojo.*;
import com.itxiaoyang.service.EmpService;
import com.itxiaoyang.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数(PageHelper)
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        //3.解析查询结果，并封装查询结果
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

    @Transactional//数据库事务控制
    @Override
    public void save(Emp emp) {
        //1.保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        //2.保存员工工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){//前面有！，表示集合如果不是空的才执行

            //遍历集合，为empId赋值,在1.中的mapper接口加了注解，能获取到empId

            //1.exprList.forEach(): 对exprList集合中的元素进行操作
            //2.empExpr -> {} :empExpr集合中的每一个工作经历对象
            //3.empExpr.setEmpId(); :为对象中的EmpId赋值
            //4.emp.getId() :要赋的值为这个

            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

    @Transactional(rollbackFor = Exception.class)//数据库事务控制,因为要执行多条sql语句
    @Override
    public void delete(List<Integer> ids) {
        //1.批量删除员工的基本信息
        empMapper.deleteByIds(ids);

        //2.批量删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //1.根据id修改员工的基本信息
        emp.setUpdateTime(LocalDateTime.now());//更新修改时间
        empMapper.updateById(emp);

        //2.根据id修改员工的工作经历
        //2.1 先删除原有的工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));//这个方法要传入的是list集合

        //2.2 再添加新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());//为对象中的EmpId赋值，指定是谁的工作经历
            });
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> allList() {
        return empMapper.allList(); // 直接返回 List
    }

    @Override
    public LoginInfo login(Emp emp) {
        //1.调用mapper接口，根据用户名和密码查询员工信息
        Emp e =empMapper.selectByNameAndPassword(emp);

        //2.判断是否存在员工，存在则返回员工信息
        if (e != null){
            log.info("员工登录成功:{}",e);

            //生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateToken(claims);

            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt);
        }

        //3.不存在返回null
        return null;
    }
}












//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
//        //1.设置分页参数(PageHelper)
//        PageHelper.startPage(page,pageSize);
//        //2.执行查询
//        List<Emp> empList = empMapper.list(name,gender,begin,end);
//        //3.解析查询结果，并封装查询结果
//        Page<Emp> p = (Page<Emp>) empList;
//        return new PageResult<Emp>(p.getTotal(),p.getResult());
//    }
//}
//-------------------------以下是原始的分页操作---------------------------------
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //1.调用mapper接口，查询总记录数
//        Long total = empMapper.Count();
//
//        //2.调用mapper接口，查询分页数据
//        List<Emp> list = empMapper.list((page - 1) * pageSize, pageSize);
//
//        //3.封装结果 PageResult
//        return new PageResult<Emp>(total,list);
//    }
//}
