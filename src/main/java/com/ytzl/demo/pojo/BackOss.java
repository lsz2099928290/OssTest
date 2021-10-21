package com.ytzl.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 文件上传
 * @TableName back_oss
 */
@TableName(value ="back_oss")
@Data
public class BackOss implements Serializable {
    /**
     * 文件 ID
     */
    @TableId
    private Long id;

    /**
     * URL 地址
     */
    private String fileUrl;

    /**
     * 存储在 OSS 中的文件名
     */
    private String ossName;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}