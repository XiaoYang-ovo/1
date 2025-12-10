package com.itxiaoyang.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")//拦截所有请求
@Slf4j
public class DemoFilter implements Filter {//过滤器练习示例

    //初始化方法，会在web服务器启动的时候执行，只执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("执行了过滤器方法");
    }

    //过滤方法，每次请求被处理之前都会执行
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("拦截到了请求....");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
    }


    //销毁方法，在web服务器关闭的时候执行，只执行一次
    @Override
    public void destroy() {
        log.info("销毁了过滤器方法");
    }
}
