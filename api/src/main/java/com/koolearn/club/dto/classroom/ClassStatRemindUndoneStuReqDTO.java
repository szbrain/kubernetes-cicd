package com.koolearn.club.dto.classroom;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by lilong01 on 2018/5/30.
 */
@Getter
@Setter
public class ClassStatRemindUndoneStuReqDTO extends BaseSerialization{

    private Integer classId;
    private Date startTime;
    private Date endTime;
    private String content;

}
