package com.koolearn.club.entity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *奖项
 */
@Getter
@Setter
public class PeDrawAwardItem extends BaseSerialization {

    private static final long serialVersionUID = 3790307722981016235L;
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 活动ID
     */
    private Integer activityId;
    /**
     * 奖项名称
     */
    private String itemName;
    /**
     * 奖项数量
     */
    private Integer totalNum;
    /**
     * 中奖概率
     */
    private Float probability;
    /**
     * 奖项状态（0不可用，1可用）
     */
    private Integer status;

    /**
     * 每天限制发放数
     */
    private Integer dayTotalNum;
    /**
     * 单个用户单日先走发放数
     */
    private Integer everyUserDayTotalNum;

    /**
     * 单个用户获取奖品上限
     */
    private Integer UserTotalNum;

    /**
     * 奖品ID
     */
    private Integer awardId;

    /**
     * 奖项等级
     */
    private Integer level;

    /**
     * 创建时间
     */
    private Date createTime;


    @Override
    public String toString() {
        return "PeDrawAwardItem{" +
                "id=" + id +
                ", activityId=" + activityId +
                ", itemName='" + itemName + '\'' +
                ", totalNum=" + totalNum +
                ", probability=" + probability +
                ", status=" + status +
                ", dayTotalNum=" + dayTotalNum +
                ", everyUserdayTotalNum=" + everyUserDayTotalNum +
                ", UserTotalNum=" + UserTotalNum +
                ", awardId=" + awardId +
                ", level=" + level +
                ", createTime=" + createTime +
                '}';
    }
}