package com.itxiaoyang.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itxiaoyang.mapper.OperateLogMapper;
import com.itxiaoyang.pojo.OperateLog;
import com.itxiaoyang.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private ObjectMapper objectMapper; // 用于序列化参数和返回值

    // 获取当前登录员工ID
    private Integer getCurrentEmpId() {

        return CurrentHolder.getCurrentId();
    }

    /**
     * 环绕通知：拦截 controller 包下所有 public 方法（可根据需要细化）
     */
    @Around("@annotation(com.itxiaoyang.anno.Log)")
    public Object recordOperateLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 获取操作人
        Integer empId = getCurrentEmpId();
        if (empId == null) {
            // 如果没有登录用户，可以选择不记录或记录为匿名
            return joinPoint.proceed();
        }

        // 2. 记录开始时间
        long startTime = System.currentTimeMillis();

        // 3. 执行目标方法
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            // 异常也记录？根据需求决定。此处仍记录日志（但返回值为异常信息）
            log.warn("Method execution threw exception", throwable);
            throw throwable; // 重新抛出，不影响原有流程
        } finally {
            // 4. 计算耗时
            long costTime = System.currentTimeMillis() - startTime;

            // 5. 构建 OperateLog 对象
            OperateLog logEntry = new OperateLog();
            logEntry.setOperateEmpId(empId);
            logEntry.setOperateTime(LocalDateTime.now());
            logEntry.setClassName(joinPoint.getTarget().getClass().getName());
            logEntry.setMethodName(joinPoint.getSignature().getName());

            // 序列化参数
            try {
                String paramsJson = objectMapper.writeValueAsString(joinPoint.getArgs());
                logEntry.setMethodParams(paramsJson.length() > 2000 ? paramsJson.substring(0, 2000) : paramsJson);
            } catch (JsonProcessingException e) {
                logEntry.setMethodParams("[JSON_PARSE_ERROR]");
                log.error("Failed to serialize method params", e);
            }

            // 序列化返回值
            try {
                String resultJson = objectMapper.writeValueAsString(result);
                logEntry.setReturnValue(resultJson.length() > 2000 ? resultJson.substring(0, 2000) : resultJson);
            } catch (JsonProcessingException e) {
                logEntry.setReturnValue("[JSON_PARSE_ERROR]");
                log.error("Failed to serialize return value", e);
            }

            logEntry.setCostTime(costTime);

            //6.同步保存
            operateLogMapper.insert(logEntry);

//            // 6. 异步保存日志（避免影响主业务性能）
//            saveOperateLog(logEntry);
        }

        return result;
    }

//    // 可选：异步保存（需配合 @EnableAsync 和 @Async）
//    private void saveOperateLog(OperateLog logEntry) {
//        try {
//            operateLogMapper.insert(logEntry);
//        } catch (Exception e) {
//            log.error("Failed to insert operate log", e); // ✅ 正确：log 是 Logger
//        }
//    }
}