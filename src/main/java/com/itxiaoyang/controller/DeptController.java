package com.itxiaoyang.controller;

import com.itxiaoyang.anno.Log;
import com.itxiaoyang.pojo.Dept;
import com.itxiaoyang.pojo.Result;
import com.itxiaoyang.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//表示当前类是一个请求处理类
public class DeptController {
    //记录日志
    private static final Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired//
    private DeptService deptService;

//    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result list(){
//        System.out.println("查询全部部门信息");
        log.info("查询全部部门信息");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    //删除部门信息
    @Log
    @RequestMapping("/depts")
    public Result delete(Integer id){
//        System.out.println("删除部门信息" + id);
        log.info("删除部门信息" + id);
        deptService.deleteById(id);
        return Result.success();
    }

    //添加部门信息
    @Log
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){//加了这个注解就可以把前端json参数传递给对象
//        System.out.println("添加部门信息:"+dept);
        log.info("添加部门信息:"+dept);
        deptService.add(dept);
        return Result.success();
    }

    //根据id查询部门
    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable Integer id){
        System.out.println("查询部门信息:"+id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    //修改部门信息
    @Log
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
//        System.out.println("修改部门信息:"+dept);
        log.info("修改部门信息:"+dept);
        deptService.update(dept);
        return Result.success();
    }
}
