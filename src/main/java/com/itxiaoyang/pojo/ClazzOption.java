package com.itxiaoyang.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ClazzOption {//班级图表信息
    private List clazzList;// 班级列表
    private List dataList;//数据列表

    public ClazzOption() {
    }

    public ClazzOption(List clazzList, List dataList) {
        this.clazzList = clazzList;
        this.dataList = dataList;
    }
}
