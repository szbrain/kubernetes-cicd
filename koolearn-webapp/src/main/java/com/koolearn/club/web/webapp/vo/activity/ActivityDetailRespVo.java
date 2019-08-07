package com.koolearn.club.web.webapp.vo.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.koolearn.club.web.webapp.vo.award.DrawAwardItemReqVo;
import com.koolearn.club.web.webapp.vo.award.RuleVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "活动详情响应对象", description = "ActivityDetailRespVo")
public class ActivityDetailRespVo implements Serializable {

    private static final long serialVersionUID = 63978376538867808L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Integer id;
    /**
     * 班级ID
     */
    @ApiModelProperty(value = "班级ID")
    private String classId;
    /**
     * 活动名称
     */
    @ApiModelProperty(value = "活动名称")
    private String activityName;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date endTime;
    /**
     * 活动类型
     */
    @ApiModelProperty(value = "活动类型(1-抽奖 2-分享裂变 3-拼团拼课 4-红包福利)")
    private String type;
    /**
     *活动状态
     */
    @ApiModelProperty(value = "活动状态")
    private String status;

    /**
     *创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createTime;

    /**
     * 奖项
     */
    @ApiModelProperty(value = "奖项")
    private List<DrawAwardItemReqVo> awardItemReqVoList;

    /**
     *触发规则
     */
    @ApiModelProperty(value = "触发规则(1-打卡 2-分享成就卡 3-加入班级 4-活动点评 5-获得勋章  6-获得点赞")
    private String triggerRule;

    /**
     *规则
     */
    @ApiModelProperty(value = "规则")
    private RuleVo ruleVO;

    /**
     *活动页地址
     */
    @ApiModelProperty(value = "活动页地址")
    private String activityUrl;
    /**
     *H5分享地址
     */
    @ApiModelProperty(value = "H5分享地址")
    private String h5Url;
    /**
     *活动模板
     */
    @ApiModelProperty(value = "活动模板（1-转盘 2-信封）")
    private String activityTemplate;

    /**
     * 活动slogan
     */
    @ApiModelProperty(value = "活动slogan" )
    private String slogan;




}
