package com.koolearn.club.dto.award;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 检查抽奖请求对象
 */
@Getter
@Setter
public class CheckAwardReqDTO extends BaseSerialization {

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
     * 触发规则
     */
    private Short triggerRule;
    /**
     * 活动ID
     */
    private Integer activityId;

    private Date now;

    private List<Integer> learingTypeList;

    @Override
    public String toString() {
        return "CheckAwardReqDTO{" +
                "sid='" + sid + '\'' +
                ", stuId=" + stuId +
                ", classId=" + classId +
                ", triggerRule=" + triggerRule +
                ", activityId=" + activityId +
                '}';
    }
}
