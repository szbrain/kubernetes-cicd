package com.koolearn.club.dto.activity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 *红包抽象规则
 */
@Getter
@Setter
public class RedPocketRuleDTO extends BaseSerialization {


    /**
     * 主键ID
     */
    private Integer id;

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

    @Override
    public String toString() {
        return "RedPocketRuleVo{" +
                "id=" + id +
                ", everyDayAmount=" + everyDayAmount +
                ", standardAmount=" + standardAmount +
                ", floatingAmount=" + floatingAmount +
                ", status=" + status +
                '}';
    }
}