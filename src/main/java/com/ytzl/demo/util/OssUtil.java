package com.ytzl.demo.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Oss 工具类，用于操作 OSS
 */
@Data
@Component
public class OssUtil {
    //阿里云 oss 站点
    @Value("${aliyun.endPoint}")
    private String endPoint;

    //阿里云 oss 文件根目录
    @Value("${aliyun.bucketName}")
    private String bucketName;

    //阿里云 oss 公钥
    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;

    //阿里云 oss 私钥
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;

    //阿里云 oss 资源访问 url
    @Value("${aliyun.domain}")
    private String domain;

    /**
     * 设置文件上传路径（prefix + 日期 + uuid + suffix）
     */
    public String getPath(String prefix, String suffix) {
        // 生成 UUID
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 格式化日期
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        // 拼接文件路径
        String path = dateTimeFormatter.format(LocalDateTime.now()) + "/" + uuid;
        if (StringUtils.isNotEmpty(prefix)) {
            path = prefix + "/" + path;
        }
        return path + "-" + suffix;
    }

    /**
     * 上传文件
     */
    public String upload(InputStream inputStream, String path) {
        try {
            // 创建 OSSClient 实例。
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            // 上传文件到 指定 bucket
            ossClient.putObject(bucketName, path, inputStream);
            // 关闭 OSSClient
            ossClient.shutdown();
        } catch (Exception e) {
            throw new RuntimeException("上传文件失败");
        }
        return path;
    }

    /**
     * 上传文件
     */
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    /**
     * 上传文件，自定义 前后缀
     */
    public String uploadSuffix(byte[] data, String prefix ,String suffix) {
        return upload(data, getPath(prefix, suffix));
    }

    /**
     * 上传文件，自定义 前后缀
     */
    public String uploadSuffix(InputStream inputStream, String prefix, String suffix) {
        return upload(inputStream, getPath(prefix, suffix));
    }

    /**
     * 获取文件 url
     */
    public String getUrl(String key) {
        // 用于保存 url 地址
        URL url = null;
        try {
            // 创建 OSSClient 实例。
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            // 设置 url 过期时间(10 年)
            Date expiration = new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365 * 10);
            // 获取 url 地址
            url = ossClient.generatePresignedUrl(bucketName, key, expiration);
            // 关闭 OSSClient
            ossClient.shutdown();
        } catch (Exception e) {
            throw new RuntimeException("获取文件 url 失败");
        }
        return url != null ? url.toString() : null;
    }

}