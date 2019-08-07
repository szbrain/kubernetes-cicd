package com.koolearn.club.dto.classroom;

import com.koolearn.club.dto.BaseSerialization;
import com.koolearn.club.entity.PeStudent;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lilong01 on 2018/5/30.
 */
@Getter
@Setter
public class ClassStatDoneStuListRespDTO extends BaseSerialization{

    private PeStudent student;
    private Integer dayCount;
    private Integer count;
    private Integer stuId;

}
