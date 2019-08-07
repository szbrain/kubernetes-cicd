package com.koolearn.club.dto.activity;

import com.koolearn.club.dto.award.DrawAwardItemReqDTO;
import com.koolearn.club.dto.award.RuleDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
public class ActivityReqDTO implements Serializable{

    private static final long serialVersionUID = -1386179863569769456L;
    /**
     * 班级ID
     */

    private String classId;
    /**
     * 活动名称
     */

    private String activityName;
    /**
     * 开始时间
     */

    private Date startTime;
    /**
     * 结束时间
     */

    private Date endTime;
    /**
     * 活动类型
     */

    private Short type;

    /**
     *触发规则
     */

    private Short triggerRule;
    /**
     *规则
     */

    private RuleDTO ruleDTO;

    /**
     *奖项列表
     */
    List<DrawAwardItemReqDTO> drawAwardItemReqDTOList;

    /**
     * 红包规则
     */
    private RedPocketRuleDTO redPocketRuleDTO;

    /**
     *活动页地址
     */

    private String activityUrl;
    /**
     *H5分享地址
     */

    private String h5Url;
    /**
     *活动模板
     */
    private String activityTemplate;

    /**
     * 活动slogan
     */
    private String slogan;

    /**
     * 红包验证码
     */
    private String code;

}
