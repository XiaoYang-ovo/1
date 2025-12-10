package com.itxiaoyang.pojo;

//分页结果封装类

import java.util.List;

public class PageResult<T> {
    private Long total;//总记录数
    private List<T> rows;//结果列表

    public PageResult(Long total, List<T> list) {
        this.total = total;
        this.rows = list;
    }

    public PageResult() {
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
