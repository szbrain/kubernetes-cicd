package com.koolearn.club.entity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 抽奖记录
 */
@Getter
@Setter
public class PeLiveRedPocketRecord extends BaseSerialization {

    private static final long serialVersionUID = 119604291354381688L;
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 直播红包ID
     */
    private Integer liveRedPocketId;

    /**
     * userID
     */
    private Integer uid;

    /**
     * 中奖金额
     */
    private BigDecimal amount;

    /**
     * 创建时间
     */
    private Date createTime;

}
