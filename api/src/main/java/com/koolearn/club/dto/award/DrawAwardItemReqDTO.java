package com.koolearn.club.dto.award;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by lvyangjun on 2018/4/18.
 */
@Getter
@Setter
public class DrawAwardItemReqDTO implements Serializable{

    private static final long serialVersionUID = 6287651327914503358L;
    /**
     * 主键ID
     */

    private Integer id;

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
     * 每天限制发放数
     */

    private Integer dayTotalNum;

    /**
     * 单个用户获取奖品上限
     */

    private Integer UserTotalNum;

    /**
     * 奖品ID
     */
    private Integer awardId;

}
