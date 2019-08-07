package com.koolearn.club.dto.learning;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by lvyangjun on 2018/05/31.
 */
@Getter
@Setter
public class LearningHistoryReqDTO extends BaseSerialization {

    /**
     * 班级ID
     */
    private int classId;
    /**
     * 学生ID
     */
    private int stuId;
    /**
     * 日期
     */
    private Date date;
    /**
     * 第几页
     */
    private int offset;
    /**
     * 分页大小
     */
    private int pageSize;

    @Override
    public String toString() {
        return "LearningHistoryReqDTO{" +
                "classId=" + classId +
                ", stuId=" + stuId +
                ", date=" + date +
                ", offset=" + offset +
                ", pageSize=" + pageSize +
                '}';
    }
}
