package com.koolearn.club.web.webapp.vo.award;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by lvyangjun on 2018/4/18.
 */
@Getter
@Setter
@ApiModel(value = "编辑奖品请求对象", description = "AwardEditReqVo")
public class AwardEditReqVo implements Serializable {


    private static final long serialVersionUID = -7682384700718331244L;
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID" )
    private Integer id;

    /**
     * 奖品名称
     */
    @ApiModelProperty(value = "奖品名称" )
    private String awardName;
    /**
     * 奖品详情
     */
    @ApiModelProperty(value = "奖品详情" )
    private String awardInfo;
    /**
     * 价格
     */
    @ApiModelProperty(value = "价格" )
    private String price;
    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址" )
    private String imgUrl;
    /**
     * 奖品类型
     */
    @ApiModelProperty(value = "奖品类型" )
    private Integer awardType;
    /**
     * 奖品状态
     */
    @ApiModelProperty(value = "奖项类型" )
    private Integer status;

    @Override
    public String toString() {
        return "AwardEditReqVo{" +
                "id=" + id +
                ", awardName='" + awardName + '\'' +
                ", awardInfo='" + awardInfo + '\'' +
                ", price='" + price + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", awardType=" + awardType +
                ", status=" + status +
                '}';
    }
}
