package com.itxiaoyang.exception;

import com.aliyun.oss.ServiceException;
import com.itxiaoyang.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {//全局异常处理
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.error("服务器异常: ",e);
        return Result.error("服务器异常ovo");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){//捕获数据库主键重复异常
        log.error("业务异常: ",e);
        String message = e.getMessage();//获取异常信息
        int i = message.indexOf("Duplicate entry");//获取索引的位置
        String errMsg = message.substring(i);//拿到报错的具体原因
        String[] arr = errMsg.split(" ");//根据空格分开errMsg
        return Result.error("【"+arr[2]+"】已存在");//返回非常具体的错误原因
    }
}
