package com.koolearn.club.service;


import com.koolearn.club.dto.activity.RedPocketRuleDTO;
import com.koolearn.club.utils.JSONResult;

public interface IRedPocketRuleService {


    /**
     * 添加红包规则
     *
     * @param redPocketRuleDTO
     * @return
     */
    JSONResult addRedPocketRule(RedPocketRuleDTO redPocketRuleDTO, Integer activityId);



    /**
     * 修改红包规则
     *
     * @param redPocketRuleDTO
     * @return
     */
    JSONResult editRedPocketRule(RedPocketRuleDTO redPocketRuleDTO, Integer activityId);



    /**
     * 根据活动ID查找红包规则
     * @param activityId
     * @return
     */
    JSONResult<RedPocketRuleDTO> findByActivityId(Integer activityId);


}
