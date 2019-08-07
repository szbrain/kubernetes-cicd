package com.koolearn.club.dto.activity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lvyangjun on 2018/4/18.
 */
@Getter
@Setter
public class RedPocketRecordListReqDTO extends BaseSerialization {

    /**
     * 活动ID
     */
    private int activityId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 金额下限
     */
    private BigDecimal amountMin;

    /**
     * 金额上限
     */
    private BigDecimal amountMax;

    /**
     * 中奖时间开始
     */
    private Date startCreateTime;

    /**
     * 中奖时间结束
     */
    private Date endCreateTime;

    /**
     * 页码
     */
    private int offset;

    /**
     * 分页大小
     */
    private int pageSize;


}
