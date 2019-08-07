package com.koolearn.club.web.webapp.vo.award;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lvyangjun on 2018/4/18.
 */
@Getter
@Setter
@ApiModel(value = "奖品列表响应对象", description = "AwardListRespVo")
public class AwardListRespVo implements Serializable {
    private static final long serialVersionUID = 239234952592998475L;
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID" )
    private Integer id;
    /**
     * 教师ID
     */
    @ApiModelProperty(value = "教师ID" )
    private Integer teachId;
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
     * 奖项类型
     */
    @ApiModelProperty(value = "奖项类型" )
    private Integer awardType;

    /**
     * 发放数量
     */
    @ApiModelProperty(value = "发放数量" )
    private Integer grantCount;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态(0-停用 1-启用)" )
    private Short status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间" )
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createTime;

    @Override
    public String toString() {
        return "AwardReqVo{" +
                "id=" + id +
                ", teachId=" + teachId +
                ", awardName='" + awardName + '\'' +
                ", awardInfo='" + awardInfo + '\'' +
                ", price='" + price + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", awardType=" + awardType +
                '}';
    }
}
