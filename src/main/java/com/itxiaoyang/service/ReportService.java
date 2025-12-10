package com.itxiaoyang.service;

import com.itxiaoyang.pojo.ClazzOption;
import com.itxiaoyang.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    //统计员工职位人数
    JobOption getEmpJobData();


    //统计员工性别人数
    List<Map<String, Object>> getEmpGenderData();


    //统计学生班级人数
    ClazzOption getStudentClazzData();

    //统计学生学历人数
    List<Map<String, Object>> getStudentDegreeData();
}
