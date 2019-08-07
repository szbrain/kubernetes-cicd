package com.koolearn.club.web.webapp.vo.activity;


import com.koolearn.club.web.webapp.vo.award.DrawAwardItemReqVo;
import com.koolearn.club.web.webapp.vo.award.RuleVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
@ApiModel(value = "创建活动对象", description = "ActivityReqVo")
public class ActivityReqVo implements Serializable {


    private static final long serialVersionUID = -3582008205165939700L;
    /**
     * 班级ID
     */
    @ApiModelProperty(value = "班级ID")
    @Min(value = 0, message = "班级ID不为空")
    private int classId;
    /**
     * 活动名称
     */
    @ApiModelProperty(value = "活动名称")
    @NotBlank(message = "活动名称不为空")
    private String activityName;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @NotBlank(message = "开始时间不为空")
    private String startTime;
    /**
     * 结束时间
     */

    @ApiModelProperty(value = "结束时间")
    @NotBlank(message = "结束时间不为空")
    private String endTime;
    /**
     * 活动类型
     */
    @ApiModelProperty(value = "活动类型(1-抽奖 2-分享裂变 3-拼团拼课 4-红包福利)")
    @Range(min = 1, max = 4, message = "活动类型范围在（1-4）")
    private Short type;

    /**
     * 触发规则
     */
    @ApiModelProperty(value = "触发规则(1-打卡 2-分享成就卡 3-加入班级 4-活动点评 5-获得勋章  6-获得点赞)")
    @Range(min = 1, max = 6, message = "触发规则范围在（1-6）")
    private Short triggerRule;
    /**
     * 规则
     */
    @ApiModelProperty(value = "规则")
    @Valid
    private RuleVo ruleVo;

    /**
     * 奖项列表
     */
    @ApiModelProperty(value = "奖项列表")
    @Valid
    @Size(min=1, max=7,message = "奖项个数必须在1-7之间")
    List<DrawAwardItemReqVo> drawAwardItemReqVoList;

    /**
     * 活动页地址
     */
    @ApiModelProperty(value = "活动页地址")
    private String activityUrl;
    /**
     * H5分享地址
     */
    @ApiModelProperty(value = "H5分享地址")

    private String h5Url;
    /**
     * 活动模板
     */
    @ApiModelProperty(value = "活动模板（1-转盘 2-信封）")
    @NotBlank(message = "活动模板不为空")
    private String activityTemplate;
    /**
     * 活动slogan
     */
    @ApiModelProperty(value = "活动slogan")
    private String slogan;


}
