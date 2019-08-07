package com.koolearn.club.dto.award;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *检查抽奖响应对象
 */
@Getter
@Setter
public class CheckAwardRespDTO extends BaseSerialization {

    /**
     * 是否可以抽奖
     */
    private boolean checkAward;

    /**
     * 活动ID
     */
    private Integer activityId;

    /**
     * 活动slogan
     */
    private String slogan;

    /**
     *触发规则
     */
    private Short triggerRule;

    /**
     * 奖项
     */
    private List<DrawAwardItemRespDTO> drawAwardItemRespDTOList;


}
