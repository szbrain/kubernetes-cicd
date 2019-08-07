package com.koolearn.club.entity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *活动
 */
@Getter
@Setter
public class PeActivity extends BaseSerialization {


    private static final long serialVersionUID = -4984277349979552606L;

    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 班级ID
     */
    private String classId;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 奖项类型
     */
    private Date startTime;
    /**
     * 创建时间
     */
    private Date endTime;
    /**
     * 活动类型
     */
    private Short type;
    /**
     *活动状态
     */
    private Short status;
    /**
     *触发规则
     */
    private Short triggerRule;
    /**
     *规则
     */
    private String rule;
    /**
     *活动页地址
     */
    private String activityUrl;
    /**
     *H5分享地址
     */
    private String h5Url;
    /**
     *活动模板
     */
    private String activityTemplate;

    /**
     * 活动slogan
     */
    private String slogan;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;



    @Override
    public String toString() {
        return "PeActivity{" +
                "id=" + id +
                ", classId='" + classId + '\'' +
                ", activityName='" + activityName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", type=" + type +
                ", status=" + status +
                ", triggerRule=" + triggerRule +
                ", rule='" + rule + '\'' +
                ", activityUrl='" + activityUrl + '\'' +
                ", h5Url='" + h5Url + '\'' +
                ", activityTemplate='" + activityTemplate + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}