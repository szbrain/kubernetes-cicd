package com.koolearn.club.dto.classroom.manage;

import com.koolearn.club.dto.common.ListParamDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by lilong01 on 2018/4/24.
 */
@Getter
@Setter
public class ClassListParamDTO extends ListParamDTO{
    private Integer teachId;
    private Integer classId;
    private String creater;
    private Integer maxClassNum;
    private Integer minClassNum;
    private String className;
    private Date startCreateTime;
    private Date endCreateTime;

}
