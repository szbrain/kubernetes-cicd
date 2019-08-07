package com.koolearn.club.dto.award;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *抽奖记录列表
 */
@Getter
@Setter
public class AppAwardRecordListReqDTO extends BaseSerialization{

    /**
     * 学生SID
     */
    private String sid;

    /**
     * 学生ID
     */
    private Integer stuId;

    /**
     * 班级ID
     */
    private Integer classId;

    /**
     * 分页Id
     */
    private Integer pageId;

    private Integer offset;

    private Integer pageSize;



    @Override
    public String toString() {
        return "AwardRecordReqListVo{" +
                "sid='" + sid + '\'' +
                ", classId=" + classId +
                ", pageId='" + pageId + '\'' +
                '}';
    }
}
