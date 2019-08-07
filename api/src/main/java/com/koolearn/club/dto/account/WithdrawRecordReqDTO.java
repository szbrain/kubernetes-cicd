package com.koolearn.club.dto.account;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 学生提现记录
 */
@Getter
@Setter
public class WithdrawRecordReqDTO extends BaseSerialization {

    private static final long serialVersionUID = 119604291354381688L;

    /**
     * userID
     */
    private Integer uid;

    /**
     * 提现金额
     */
    private BigDecimal amount;

    /**
     * 商户订单号
     */
    private String partnerTradeNo;

    /**
     * 微信订单号
     */
    private String paymentNo;

    /**
     * 支付状态（1-支付成功 0-支付失败）
     */
    private Short status;

    /**
     * 支付成功时间
     */
    private Date paymentTime;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 微信返回数据
     */
    private String responseXml;


}
