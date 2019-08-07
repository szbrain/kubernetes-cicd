package com.koolearn.club.dto.award;

import com.koolearn.club.dto.BaseSerialization;
import com.koolearn.club.entity.PeDrawAward;
import com.koolearn.club.entity.PeDrawRecord;
import com.koolearn.club.entity.PeStudent;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *抽奖记录
 */
@Getter
@Setter
public class DrawAwardRecordDTO extends PeDrawRecord {

    /**
     * 学生对象
     */
    PeStudent student;


}
