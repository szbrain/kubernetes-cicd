package com.koolearn.club.entity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *Token
 */
@Getter
@Setter
public class PeTeachToken extends BaseSerialization {


    private static final long serialVersionUID = -4984277349979552606L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 编码，换取token
     */
    private String code;
    /**
     * 教师ID
     */
    private Integer teachId;
    /**
     * 状态
     */
    private Short status;
    /**
     *  创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;



}