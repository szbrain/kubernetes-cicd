package com.koolearn.club.dto.award;

import com.koolearn.club.dto.common.ListParamDTO;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
public class AwardRecordListParamDTO extends ListParamDTO{


    private static final long serialVersionUID = -3582008205165939700L;

    /**
     * 活动ID
     */
    private int activityId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 中奖时间开始
     */
    private Date startCreateTime;

    /**
     * 中奖时间结束
     */
    private Date endCreateTime;

    /**
     * 奖品名称
     */
    private String awardName;

    /**
     *  物流地址
     */
    private String address;


}
