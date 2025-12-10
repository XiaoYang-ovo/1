package com.itxiaoyang.controller;

import com.itxiaoyang.pojo.ClazzOption;
import com.itxiaoyang.pojo.Result;
import com.itxiaoyang.pojo.JobOption;
import com.itxiaoyang.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j//记录日志
@RequestMapping("/report")
@RestController//请求处理类
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/empJobData")
    public Result getEmpJobData(){//统计员工职位人数
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }


    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){//统计员工性别人数
        log.info("统计员工性别人数");
        List<Map<String, Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    @GetMapping("/studentCountData")
    public Result getStudentClazzData(){ //统计学生班级人数
        log.info("统计学生班级人数");
        ClazzOption clazzOption = reportService.getStudentClazzData();
        return Result.success(clazzOption);
    }

    @GetMapping ("/studentDegreeData")
    public Result getStudentDegreeData(){ //统计学生学历人数
        log.info("统计学生学历人数");
        List<Map<String, Object>> degreeList = reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }
}
