package com.koolearn.club.entity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 *红包抽象规则
 */
@Getter
@Setter
public class PeRedPocketRule extends BaseSerialization {

    private static final long serialVersionUID = 3790307722981016235L;
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 活动ID
     */
    private Integer activityId;

    /**
     * 每日金额限制
     */
    private BigDecimal everyDayAmount;
    /**
     * 基准金额
     */
    private BigDecimal standardAmount;
    /**
     * 浮动金额
     */
    private BigDecimal floatingAmount;

    /**
     * 状态（1-启用 0-删除）
     */
    private Short status;

    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 修改时间
     */
    private Date updateTime;

    @Override
    public String toString() {
        return "PeRedPocketRule{" +
                "id=" + id +
                ", activityId=" + activityId +
                ", everyDayAmount=" + everyDayAmount +
                ", standardAmount=" + standardAmount +
                ", floatingAmount=" + floatingAmount +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}