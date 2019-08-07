package com.koolearn.club.entity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *奖品
 */
@Getter
@Setter
public class PeDrawAward extends BaseSerialization {


    private static final long serialVersionUID = -4984277349979552606L;

    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 教师ID
     */
    private Integer teachId;
    /**
     * 奖品名称
     */
    private String awardName;
    /**
     * 奖品详情
     */
    private String awardInfo;
    /**
     * 价格
     */
    private String price;
    /**
     * 图片地址
     */
    private String imgUrl;
    /**
     * 奖项类型
     */
    private Integer awardType;
    /**
     * 状态
      */
    private Short status;

    /**
     * 发放数量
     */
    private Integer grantCount;
    /**
     * 创建时间
     */
    private Date createTime;

    @Override
    public String toString() {
        return "PeDrawAward{" +
                "id=" + id +
                ", teachId=" + teachId +
                ", awardName='" + awardName + '\'' +
                ", awardInfo='" + awardInfo + '\'' +
                ", price='" + price + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", awardType=" + awardType +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}