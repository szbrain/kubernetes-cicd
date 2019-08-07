package com.koolearn.club.web.webapp.vo.activity;

import com.koolearn.club.dto.BaseSerialization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
@ApiModel(value = "活动详情请求对象", description = "ActivityDetailReqVo")
public class ActivityDetailReqVo extends BaseSerialization {


    private static final long serialVersionUID = -3582008205165939700L;

    /**
     * 活动ID
     */
    @ApiModelProperty(value = "活动ID" )
    @Min(value = 0, message = "活动ID不小于0")
    private Integer activityId;


}
