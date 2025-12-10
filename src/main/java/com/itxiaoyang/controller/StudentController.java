package com.itxiaoyang.controller;

import com.itxiaoyang.pojo.*;
import com.itxiaoyang.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    //分页查询
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("查询所有学生信息:{}",studentQueryParam);
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    //删除学生信息
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除学生信息:{}",ids);
        studentService.delete(ids);
        return Result.success();
    }

    //新增学生信息
    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("保存学生信息:{}",student);
        studentService.save(student);
        return Result.success();
    }

    //根据id查询学生信息
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询学生信息:{}",id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }

    //修改学生信息
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改学生信息:{}",student);
        studentService.update(student);
        return Result.success();
    }


}
