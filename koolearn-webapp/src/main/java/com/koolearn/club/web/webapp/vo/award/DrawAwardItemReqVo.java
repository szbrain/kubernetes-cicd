package com.koolearn.club.web.webapp.vo.award;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Created by lvyangjun on 2018/4/18.
 */
@Getter
@Setter
@ApiModel(value = "活动奖项", description = "DrawAwardItemReqVo")
public class DrawAwardItemReqVo implements Serializable {
    private static final long serialVersionUID = 239234952592998475L;
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID" )
    private Integer id;

    /**
     * 奖项名称
     */
    @ApiModelProperty(value = "奖项名称" )
    @NotBlank(message = "奖项名称不为空")
    private String itemName;
    /**
     * 奖项数量
     */
    @ApiModelProperty(value = "奖项数量" )
    @Min(value = 0, message = "奖项数量不小于0")
    private Integer totalNum;
    /**
     * 中奖概率
     */
    @ApiModelProperty(value = "中奖概率")
    @Range(min = 0, max = 1, message = "中奖概率(0-1之间的小数)")
    private Float probability;

    /**
     * 每天限制发放数
     */
    @ApiModelProperty(value = "每天限制发放数")
    @Min(value = 0, message = "每天限制发放数不小于0")
    private Integer dayTotalNum;

    /**
     * 单个用户获取奖品上限
     */
    @ApiModelProperty(value = "单个用户获取奖品上限" )
    @Min(value = 0, message = "单个用户获取奖品上限不小于0")
    private Integer UserTotalNum;

    /**
     * 奖品ID
     */
    @ApiModelProperty(value = "奖品ID" )
    @Min(value = 0, message = "奖品ID不为空")
    private Integer awardId;

    /**
     * 奖品名称
     */
    @ApiModelProperty(value = "奖品名称" )
    private String awardName;


}
