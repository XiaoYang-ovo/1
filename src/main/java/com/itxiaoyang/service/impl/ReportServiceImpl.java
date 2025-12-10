package com.itxiaoyang.service.impl;

import com.itxiaoyang.mapper.EmpMapper;
import com.itxiaoyang.mapper.StudentMapper;
import com.itxiaoyang.pojo.ClazzOption;
import com.itxiaoyang.pojo.JobOption;
import com.itxiaoyang.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper StudentMapper;

    @Override
    public JobOption getEmpJobData() {
        //1.调用mapper接口，获取统计数据
        //map: pos=班主任,num=10
        List<Map<String, Object>> list = empMapper.countEmpJobData();

        //2.组装结果，并返回
        //遍历map，获取pos和num封装到新的集合
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList,dataList);
    }


    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public ClazzOption getStudentClazzData() {
        //1.调用mapper接口，获取统计数据
        List<Map<String, Object>> list = StudentMapper.countStudentClazzData();

        //2.组装结果，并返回
        //遍历map，获取pos和num封装到新的集合
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("clazz")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new ClazzOption(clazzList,dataList);
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return StudentMapper.countStudentDegreeData();
    }
}
