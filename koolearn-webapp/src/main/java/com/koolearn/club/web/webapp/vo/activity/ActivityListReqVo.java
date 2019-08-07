package com.koolearn.club.web.webapp.vo.activity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
@ApiModel(value = "活动列表请求对象", description = "ActivityListReqVo")
public class ActivityListReqVo implements Serializable {


    private static final long serialVersionUID = -3582008205165939700L;
    /**
     * 班级ID
     */
    @ApiModelProperty(value = "班级ID")
    @Min(value = 0, message = "班级ID不小于0")
    @NotNull(message = "班级ID不为空")
    private Integer classId;

    /**
     * 活动ID
     */
    @ApiModelProperty(value = "活动ID")
    private String activityId;

    /**
     * 活动名称
     */
    @ApiModelProperty(value = "活动名称")
    private String activityName;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private String startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private String endTime;
    /**
     * 活动类型
     */
    @ApiModelProperty(value = "活动类型")
    @Range(min = 1, max = 4, message = "活动类型范围在（1-4）")
    private Short type;

    /**
     * 活动状态
     */
    @ApiModelProperty(value = "活动状态")
    @Range(min = 1, max = 2, message = "活动状态范围在（1-2）")
    private Short status;

    /**
     * 参与人数下限
     */
    @ApiModelProperty(value = "参与人数下限")
    @Min(value = 0, message = "参与人数下限不小于0")
    private int joinCountLowerLimit;

    /**
     * 参与人数上限
     */
    @ApiModelProperty(value = "参与人数上限")
    @Min(value = 0, message = "参与人数上限不小于0")
    private int joinCountUpperLimit;

    @ApiModelProperty(value = "页码")
    @Min(value = 0, message = "页码不小于0")
    private int offset;

    @ApiModelProperty(value = "分页大小")
    @Min(value = 1, message = "分页大小必须大于0")
    private int pageSize;

}
