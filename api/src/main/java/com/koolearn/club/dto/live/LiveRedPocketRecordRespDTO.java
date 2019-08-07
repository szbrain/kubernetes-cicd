package com.koolearn.club.dto.live;

import com.koolearn.club.entity.PeLiveRedPocketRecord;
import com.koolearn.club.entity.PeStudent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LiveRedPocketRecordRespDTO extends PeLiveRedPocketRecord {
    /**
     * 学生对象
     */
    PeStudent student;
}
