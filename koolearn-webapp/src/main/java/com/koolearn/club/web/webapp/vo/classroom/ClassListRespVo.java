package com.koolearn.club.web.webapp.vo.classroom;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.koolearn.club.dto.BaseSerialization;
import com.koolearn.club.dto.classroom.manage.ClassListDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by lilong01 on 2018/4/24.
 */
@Setter
@Getter
@ApiModel(value = "班级列表对象", description = "ClassListVo")
public class ClassListRespVo extends BaseSerialization {
    @ApiModelProperty(value = "班级ID")
    private Integer classId;
    @ApiModelProperty(value = "班级名称")
    private String className;
    @ApiModelProperty(value = "班级创建者")
    private String creater;
    @ApiModelProperty(value = "班级人数")
    private Integer classNum;
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd"  ,timezone = "GMT+8")
    private Date createTime;
    @ApiModelProperty(value = "激活码")
    private String identifyingCode;


    public static ClassListRespVo getInstance(ClassListDTO classListDTO) {
        ClassListRespVo classListRespVo = new ClassListRespVo();
        classListRespVo.setCreateTime(classListDTO.getCreateTime());
        classListRespVo.setClassNum(classListDTO.getClassNum());
        classListRespVo.setClassId(classListDTO.getId());
        classListRespVo.setClassName(classListDTO.getName());
        classListRespVo.setCreater(classListDTO.getTeacher());
        classListRespVo.setIdentifyingCode(classListDTO.getIdentifyingCode());
        return classListRespVo;
    }
}
