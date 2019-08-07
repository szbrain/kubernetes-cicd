package com.koolearn.club.dto.award;


import com.koolearn.club.entity.PeRedPocketRecord;
import com.koolearn.club.entity.PeRedPocketRule;
import com.koolearn.club.entity.PeStudent;
import lombok.Getter;
import lombok.Setter;

/**
 * 红包抽奖记录
 */
@Getter
@Setter
public class RedPocketRecordDTO extends PeRedPocketRecord {

    /**
     * 学生对象
     */
    PeStudent student;


}
