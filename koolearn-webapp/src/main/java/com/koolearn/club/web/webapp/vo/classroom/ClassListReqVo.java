package com.koolearn.club.web.webapp.vo.classroom;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.koolearn.club.dto.BaseSerialization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.util.Date;

/**
 * Created by lilong01 on 2018/4/24.
 */
@Setter
@Getter
@ApiModel(value = "班级列表查询对象", description = "ClassListParamVo")
public class ClassListReqVo extends BaseSerialization {
    //@ApiModelProperty(value = "教师ID")
    //@NotNull(message = "教师ID不能为空")
    private Integer teachId;
    @ApiModelProperty(value = "班级ID")
    private Integer classId;
    @ApiModelProperty(value = "班级创建者")
    private String creater;
    @ApiModelProperty(value = "最大班级人数")
    private Integer maxClassNum;
    @ApiModelProperty(value = "最小班级人数")
    private Integer minClassNum;
    @ApiModelProperty(value = "班级名称")
    private String className;
    @ApiModelProperty(value = "开始创建时间")
    @JsonFormat(pattern="yyyy-MM-dd"  ,timezone = "GMT+8")
    private Date startCreateTime;
    @ApiModelProperty(value = "结束创建时间")
    @JsonFormat(pattern="yyyy-MM-dd"  ,timezone = "GMT+8")
    private Date endCreateTime;

    @ApiModelProperty(value = "页码" )
    @Range(min=0,message = "页码不小于0")
    private int offset;
    @ApiModelProperty(value = "分页大小" )
    @Range(min=1,message = "分页大小必须大于0")
    private int pageSize;

}
