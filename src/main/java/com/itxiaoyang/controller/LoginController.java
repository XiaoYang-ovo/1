package com.itxiaoyang.controller;

import com.itxiaoyang.pojo.Emp;
import com.itxiaoyang.pojo.LoginInfo;
import com.itxiaoyang.pojo.Result;
import com.itxiaoyang.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 登录控制器
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    // 登录的方法
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){//这个注解可以把前端json参数传递给对象
        log.info("员工登录:{}",emp);
        LoginInfo info =empService.login(emp);
        if (info != null){
            return Result.success(info);
        }
        return Result.error("用户名或密码错误");
    }
}
