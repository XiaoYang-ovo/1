package com.itxiaoyang.config;

import com.itxiaoyang.interceptor.DemoInterceptor;
import com.itxiaoyang.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//表示这是一个配置类
public class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    private DemoInterceptor demoInterceptor;      //这个是演示用的拦截器

//    @Autowired
//    private TokenInterceptor tokenInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(tokenInterceptor)
//                .addPathPatterns("/**")//添加拦截器,拦截所有请求
//                .excludePathPatterns("/login");//添加不拦截路径
//    }
}
