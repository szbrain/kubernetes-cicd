package com.koolearn.club.web.webapp.vo.awardrecord;

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
@ApiModel(value = "中奖名单列表响应对象", description = "AwardRecordListRespVo")
public class AwardRecordListRespVo implements Serializable {
    private static final long serialVersionUID = 239234952592998475L;

    /**
     * 时间开始
     */
    @ApiModelProperty(value = "中奖时间" )
    @JsonFormat(pattern="yyyy-MM-dd HH:mm"  ,timezone = "GMT+8")
    private Date createTime;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称" )
    private String nickname;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名" )
    private String name;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式" )
    private String mobile;

    /**
     * 奖项名称
     */
    @ApiModelProperty(value = "奖项名称" )
    private String awardItemName;

    /**
     * 奖品名称
     */
    @ApiModelProperty(value = "奖品名称" )
    private String awardName;

    /**
     *  物流地址
     */
    @ApiModelProperty(value = "物流地址" )
    private String address;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注" )
    private String remarks;




}
