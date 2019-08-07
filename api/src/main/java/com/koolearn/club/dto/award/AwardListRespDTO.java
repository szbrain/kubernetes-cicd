package com.koolearn.club.dto.award;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lvyangjun on 2018/4/18.
 */
@Getter
@Setter
public class AwardListRespDTO implements Serializable {
    private static final long serialVersionUID = 239234952592998475L;
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
     * 发放数量
     */
    private Integer grantCount;
    /**
     * 状态
     */
    private Short status;
    /**
     * 创建时间
     */
    private Date createTime;

    @Override
    public String toString() {
        return "AwardReqVo{" +
                "id=" + id +
                ", teachId=" + teachId +
                ", awardName='" + awardName + '\'' +
                ", awardInfo='" + awardInfo + '\'' +
                ", price='" + price + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", awardType=" + awardType +
                '}';
    }
}
