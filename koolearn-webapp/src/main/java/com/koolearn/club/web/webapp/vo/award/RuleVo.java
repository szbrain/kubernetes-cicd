package com.koolearn.club.web.webapp.vo.award;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by lvyangjun on 2018/4/23.
 */
@Setter
@Getter
@ApiModel(value = "活动规则", description = "RuleVo")
public class RuleVo implements Serializable {

    private static final long serialVersionUID = 5840846995977338888L;
    @ApiModelProperty(value = "开始时间" )
    @Pattern(regexp = "[0-2][0-9]:[0-5][0-9]",message = "开始时间格式不正确")
    private String startTime;
    @ApiModelProperty(value = "结束时间" )
    @Pattern(regexp = "[0-2][0-9]:[0-5][0-9]",message = "结束时间格式不正确")
    private String endTime;
    @ApiModelProperty(value = "限制类型（0-无限制 1-图片 2-文字 3-语音）" )
    private Short type;
    @ApiModelProperty(value = "每人每天参与上限" )
    private Integer everyDaylimit;

    @Override
    public String toString() {
        return "RuleVo{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", type=" + type +
                ", everyDaylimit=" + everyDaylimit +
                '}';
    }
}
