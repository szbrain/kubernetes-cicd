package com.koolearn.club.web.webapp.vo.awardrecord;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.koolearn.club.dto.BaseSerialization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by lvyangjun on 2018/4/18.
 */
@Getter
@Setter
@ApiModel(value = "奖品列表导出请求对象", description = "AwardRecordListReqExportVo")
public class AwardRecordListReqExportVo extends BaseSerialization {

    /**
     * 活动ID
     */
    @ApiModelProperty(value = "活动ID" )
    @NotNull
    private int activityId;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称" )
    private String nickname;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式" )
    private String mobile;

    /**
     * 中奖时间开始
     */
    @ApiModelProperty(value = "中奖时间开始" )
    @JsonFormat(pattern="yyyy-MM-dd"  ,timezone = "GMT+8")
    private Date startCreateTime;

    /**
     * 中奖时间结束
     */
    @ApiModelProperty(value = "中奖时间结束" )
    @JsonFormat(pattern="yyyy-MM-dd"  ,timezone = "GMT+8")
    private Date endCreateTime;


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

}
