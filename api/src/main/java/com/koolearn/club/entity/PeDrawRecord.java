package com.koolearn.club.entity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *抽奖记录
 */
@Getter
@Setter
public class PeDrawRecord extends BaseSerialization {

    private static final long serialVersionUID = 119604291354381688L;
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 活动ID
     */
    private Integer activityId;

    /**
     * userID
     */
    private Integer uid;

    /**
     * 用户名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;
    /**
     * 收货地址
      */
    private String address;
    /**
     * 快递公司
     */
    private String postalInc;
    /**
     * 快递编号
      */
    private String postalCode;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 奖品等级
     */
    private Integer level;
    /**
     * 奖品名称
     */
    private String awardName;

    /**
     * 奖项ID
     */
    private Integer awardItemId;
    /**
     * 奖项名称
     */
    private String awardItemName;
    /**
     * 创建时间
     */
    private Date createTime;

    @Override
    public String toString() {
        return "PeDrawRecord{" +
                "id=" + id +
                ", activityId=" + activityId +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", postalInc='" + postalInc + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", level=" + level +
                ", awardName='" + awardName + '\'' +
                ", awardItemName='" + awardItemName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
