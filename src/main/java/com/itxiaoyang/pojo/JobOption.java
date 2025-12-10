package com.itxiaoyang.pojo;

import lombok.Data;

import java.util.List;

@Data
public class JobOption {//职位人数图标封装类

    private List jobList;// 职位列表
    private List dataList;//数据列表

    public JobOption() {
    }

    public JobOption(List jobList, List dataList) {
        this.jobList = jobList;
        this.dataList = dataList;
    }
}
