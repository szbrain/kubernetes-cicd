package com.koolearn.club.dto.classroom;

import com.koolearn.club.dto.BaseSerialization;
import com.koolearn.club.dto.common.PageDTO;
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
public class ClassStatDTO extends BaseSerialization {
    private Date statTime;
    private Date openTime;
    private int stuCount;
    private int UndoneTaskStuCount;
    private int quitStuCount;
    List<PeClassStat> classStatList;
}
