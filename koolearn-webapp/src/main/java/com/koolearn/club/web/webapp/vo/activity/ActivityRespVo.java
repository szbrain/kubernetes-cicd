package com.koolearn.club.web.webapp.vo.activity;

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
@ApiModel(value = "活动对象", description = "ActivityRespVo")
public class ActivityRespVo implements Serializable {

    private static final long serialVersionUID = 63978376538867808L;
    @ApiModelProperty(value = "姓名")
    /**
     * 主键ID
     */
    private Integer id;
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
     *活动状态
     */
    private Short status;

    /**
     *创建时间
     */
    private Date createTime;



}
