package com.itxiaoyang.interceptor;

import com.itxiaoyang.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
//令牌校验的拦截器
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //1.获取请求路径
//        String requestURI = request.getRequestURI();
//
//        //2.判断路径是否是登录（路径包含/login），如果是登录，则放行
//        if (requestURI.contains("/login")) {
//            log.info("登录，放行");
//            return true;
//        }

        //3.获取请求头中的token
        String token = request.getHeader("token");

        //4.判断token是否为空，为空则提示用户登录，返回错误信息401
        if (token == null || token.equals("")) {
            log.info("令牌为空，401");
            response.setStatus(401);
            return false;
        }

        //5.token如果存在，则调用JwtUtils的parse方法，解析token
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("令牌为空，401");
            response.setStatus(401);
            return false;
        }

        //6.如果解析成功，则放行
        log.info("令牌解析成功，放行");
        return true;
    }
}
