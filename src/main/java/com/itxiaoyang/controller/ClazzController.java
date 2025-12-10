package com.itxiaoyang.controller;

import com.itxiaoyang.pojo.*;
import com.itxiaoyang.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    ClazzService clazzService;

    //分页查询
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("查询全部班级信息:{}",clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    //删除班级信息
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除班级信息:{}",id);
        clazzService.delete(id);
        return Result.success();
    }

    //新增班级信息
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("保存班级信息:{}",clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    //根据id查询班级信息
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询班级信息:{}",id);
        Clazz clazz = clazzService.getInfo(id);
        return Result.success(clazz);
    }

    //修改班级信息
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级信息:{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    //查询所有班级信息
    @GetMapping("/list")
    public Result list(){
        log.info("查询所有班级信息");
        List<Clazz> list = clazzService.allList();
        return Result.success(list);
    }
}
