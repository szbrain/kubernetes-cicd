package com.koolearn.club.web.webapp.vo.award;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
@ApiModel(value = "奖品请求列表", description = "AwardListReqVo")
public class AwardListReqVo implements Serializable {


    private static final long serialVersionUID = -3582008205165939700L;

    /**
     * 教师ID
     */
    //@ApiModelProperty(value = "教师ID" )
    private int teachId;

    @ApiModelProperty(value = "页码")
    @Range(min=0,message = "页码不小于0")
    private int offset;
    @ApiModelProperty(value = "分页大小")
    @Range(min=1,message = "分页大小必须大于0")
    private int pageSize;


}
