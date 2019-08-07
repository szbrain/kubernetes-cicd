package com.koolearn.club.web.webapp.vo.activity;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
@ApiModel(value = "活动列表响应对象", description = "ActivityListRespVo")
public class ActivityListRespVo implements Serializable {

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
    @ApiModelProperty(value = "活动类型")
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
     * 参与人数
     */
    @ApiModelProperty(value = "参与人数")
    private int  joinUserTotal;
    /**
     * 参与次数
     */
    @ApiModelProperty(value = "参与次数")
    private int joinTotal;



}
