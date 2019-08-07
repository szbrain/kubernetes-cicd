package com.koolearn.club.entity;


import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 微信消息记录日志
 */
@Getter
@Setter
public class PeWXMessageRecord extends BaseSerialization {


    private static final long serialVersionUID = -6887318530476245828L;
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * userID
     */
    private Integer classId;

    /**
     * 接收的OpenId
     */
    private String openId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 微信返回数据
     */
    private String responseJson;
}
