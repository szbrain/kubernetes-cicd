package com.koolearn.club.dto.activity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
public class ActivityListReqDTO implements Serializable {


    private static final long serialVersionUID = -7633933200326556366L;
    /**
     * 班级ID
     */
    private int classId;

    /**
     * 活动ID
     */
    private String activityId;

    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 活动类型
     */
    private Short type;

    /**
     * 活动状态
     */
    private Short status;

    /**
     * 参与人数下限
     */
    private int joinCountLowerLimit;

    /**
     * 参与人数上限
     */
    private int joinCountUpperLimit;


    private int offset;

    private int pageSize;


}
