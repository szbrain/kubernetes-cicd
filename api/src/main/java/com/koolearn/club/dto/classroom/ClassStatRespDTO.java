package com.koolearn.club.dto.classroom;

import com.koolearn.club.dto.BaseSerialization;
import com.koolearn.club.entity.PeClassStat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/28.
 */
@Getter
@Setter
public class ClassStatRespDTO extends BaseSerialization {
    private int stuCount;
    private int UndoneTaskStuCount;
    private Date openTime;
}
