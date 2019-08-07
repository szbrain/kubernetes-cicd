package com.koolearn.club.dto.award;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by lvyangjun on 2018/4/18.
 */
@Getter
@Setter
public class AwardEditReqDTO implements Serializable {


    private static final long serialVersionUID = 8437048373811328452L;
    /**
     * 主键ID
     */
    private Integer id;

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
     * 奖品类型
     */
    private Integer awardType;
    /**
     * 奖品状态
     */
    private Integer status;

    @Override
    public String toString() {
        return "AwardEditReqVo{" +
                "id=" + id +
                ", awardName='" + awardName + '\'' +
                ", awardInfo='" + awardInfo + '\'' +
                ", price='" + price + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", awardType=" + awardType +
                ", status=" + status +
                '}';
    }
}
