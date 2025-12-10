package com.itxiaoyang.controller;

import com.itxiaoyang.pojo.Result;
import com.itxiaoyang.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile  file) throws Exception {

        log.info("文件上传:{}", file.getOriginalFilename());//这个方法是获取文件的原始名称
        //将文件交给oss存储(阿里云)
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传成功:{}", url);

        return Result.success(url);
    }

//    //文件上传到本地磁盘的方法
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile  file) throws IOException {
//        log.info("接受到的参数:{},{},{}",name,age,file);//记录日志
//        //获取上传的文件名
//        String originalFilename = file.getOriginalFilename();
//        //生成新的文件名
//        String newFileName = UUID.randomUUID().toString() + originalFilename;
//        //保存文件
//        file.transferTo(new File("D:/javalianxi/oioi/"+newFileName));
//        return Result.success();
//    }
}
