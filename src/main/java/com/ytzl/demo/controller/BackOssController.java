package com.ytzl.demo.controller;

import com.ytzl.demo.R.R;
import com.ytzl.demo.R.RD;
import com.ytzl.demo.pojo.BackOss;
import com.ytzl.demo.service.BackOssService;
import com.ytzl.demo.util.OssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/oss/back-oss")
@Api(tags = "文件上传")
public class BackOssController {

    @Autowired
    private OssUtil ossUtil;
    @Autowired
    private BackOssService backOssService;

    @ApiOperation(value = "上传文件")
    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file) {
        // 用于保存文件 url
        String url = null;
        // 用于保存文件信息
        BackOss backOss = new BackOss();
        try {
            //获取图片点后的后缀
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            System.out.println("------->文件后缀"+ext);
            //获取当前时间的年月日时分秒
            String format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
            //设置图片的新名字
            String newFileName = format + UUID.randomUUID().toString().replaceAll("-", "") + ext;
            // 获取文件上传路径
            url = ossUtil.uploadSuffix(file.getInputStream(), "aliyun", newFileName);
            // 保存文件路径到数据库中
            backOss.setFileName(newFileName);
            backOss.setOssName(url);
            backOss.setFileUrl(ossUtil.getUrl(url));
            //写入数据库
            backOssService.save(backOss);
        } catch (IOException e) {
            return RD.ok(e);
        }
        return RD.ok(backOss);
    }

    @ApiOperation(value = "获取所有文件信息")
    @GetMapping("/getAll")
    public R getAll() {
        return RD.ok(backOssService.list());
    }
}

