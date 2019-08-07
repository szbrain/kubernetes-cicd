package com.koolearn.club.web.webapp.vo.award;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * Created by lvyangjun on 2018/4/18.
 */
@Getter
@Setter
@ApiModel(value = "删除活动奖项对象", description = "DeleteAwardItemReqVo")
public class DeleteAwardItemReqVo  {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID" )
    @Min(value = 0, message = "主键ID不小于0")
    private Integer id;


}
