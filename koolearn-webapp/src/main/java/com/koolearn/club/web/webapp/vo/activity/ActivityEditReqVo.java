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
@ApiModel(value = "编辑活动对象", description = "ActivityEditReqVo")
public class ActivityEditReqVo implements Serializable {


    private static final long serialVersionUID = -3582008205165939700L;

    /**
     * 活动ID
     */
    @ApiModelProperty(value = "活动ID" )
    @Min(value = 0, message = "活动ID不为空")
    private Integer activityId;

    /**
     *触发规则
     */
    @ApiModelProperty(value = "触发规则" )
    @Range(min=1, max=6,message = "触发规则范围在（1-6）")
    private Short triggerRule;
    /**
     *规则
     */
    @ApiModelProperty(value = "规则" )
    @Valid
    private RuleVo ruleVo;

    /**
     *奖项列表
     */
    @ApiModelProperty(value = "奖项列表" )
    @Valid
    @Size(min=1, max=7,message = "奖项个数必须在1-7之间")
    List<DrawAwardItemReqVo> drawAwardItemReqVoList;

    /**
     *活动页地址
     */
    @ApiModelProperty(value = "活动页地址" )
    private String activityUrl;
    /**
     *H5分享地址
     */
    @ApiModelProperty(value = "H5分享地址" )
    private String h5Url;
    /**
     *活动模板
     */
    @ApiModelProperty(value = "活动模板" )
    @NotBlank(message = "活动模板不为空")
    private String activityTemplate;

    /**
     * 活动slogan
     */
    @ApiModelProperty(value = "活动slogan" )
    private String slogan;


    @Override
    public String toString() {
        return "ActivityEditReqVo{" +
                "triggerRule=" + triggerRule +
                ", ruleVo=" + ruleVo +
                ", drawAwardItemReqVoList=" + drawAwardItemReqVoList +
                ", activityUrl='" + activityUrl + '\'' +
                ", h5Url='" + h5Url + '\'' +
                ", activityTemplate='" + activityTemplate + '\'' +
                '}';
    }
}
