package com.koolearn.club.entity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 学生账户
 */
@Getter
@Setter
public class PeStudentAccount extends BaseSerialization {

    private static final long serialVersionUID = 119604291354381688L;
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * userID
     */
    private Integer uid;

    /**
     * 账户余额
     */
    private BigDecimal blance;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
