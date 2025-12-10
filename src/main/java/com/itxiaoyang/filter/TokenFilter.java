package com.itxiaoyang.filter;

import com.itxiaoyang.utils.CurrentHolder;
import com.itxiaoyang.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取请求路径
        String requestURI = request.getRequestURI();

        //2.判断路径是否是登录（路径包含/login），如果是登录，则放行
        if (requestURI.contains("/login")) {
            log.info("登录，放行");
            filterChain.doFilter(request, response);
            return;
        }

        //3.获取请求头中的token
        String token = request.getHeader("token");

        //4.判断token是否为空，为空则提示用户登录，返回错误信息401
        if (token == null || token.equals("")) {
            log.info("令牌为空，401");
            response.setStatus(401);
            return;
        }

        //5.token如果存在，则调用JwtUtils的parse方法，解析token
        try {
            Claims claims = JwtUtils.parseToken(token);

            //获取当前登录用户的id
            Integer empId = Integer.valueOf(claims.get("id").toString());
            //把当前登录用户的id保存到ThreadLocal中，以后其他地方使用
            CurrentHolder.setCurrentId(empId);
            log.info("当前登录用户id为：{}，将其存入ThreadLocal", empId);

        } catch (Exception e) {
            log.info("令牌为空，401");
            response.setStatus(401);
            return;
        }

        //6.如果解析成功，则放行
        log.info("令牌解析成功，放行");
        filterChain.doFilter(request, response);

        //7.最后，在过滤器中，移除ThreadLocal中的数据
        CurrentHolder.remove();
    }
}
