package com.koolearn.club.dto.award;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by lvyangjun on 2018/4/18.
 */
@Getter
@Setter
public class AwardRespDTO implements Serializable {
    private static final long serialVersionUID = 239234952592998475L;

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

    @Override
    public String toString() {
        return "AwardRespVo{" +
                "awardName='" + awardName + '\'' +
                ", awardInfo='" + awardInfo + '\'' +
                ", price='" + price + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", awardType=" + awardType +
                '}';
    }
}
