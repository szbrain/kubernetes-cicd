package com.koolearn.club.dto.activity;

import com.koolearn.club.dto.award.DrawAwardItemRespDTO;
import com.koolearn.club.dto.award.RuleDTO;
import com.koolearn.club.entity.PeActivity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
public class ActivityDetailRespDTO extends PeActivity{

    private static final long serialVersionUID = -1386179863569769456L;

    /**
     * 奖项
     */
    private List<DrawAwardItemRespDTO> drawAwardItemRespDTOList;

    /**
     *规则
     */
    private RuleDTO ruleDTO;

    private RedPocketRuleDTO redPocketRuleDTO;

    /**
     * 参与人数
     */
    int  joinUserTotal;
    /**
     * 参与次数
     */
    int joinTotal;

    /**
     * 当日参与人数
     */
    private int  todayJoinUserTotal;
    /**
     * 当日参与次数
     */
    private int todayJoinTotal;

    /**
     * 今日累计发放金额
     */
    private BigDecimal todayAmount;
    /**
     * 活动累计发放金额
     */
    private BigDecimal allAmount;



}
