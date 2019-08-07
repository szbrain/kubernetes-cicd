package com.koolearn.club.impl;

import com.koolearn.club.constants.activity.RedPocketErrorCodeEnum;
import com.koolearn.club.dto.activity.RedPocketRuleDTO;
import com.koolearn.club.entity.PeRedPocketRule;
import com.koolearn.club.mapper.RedPocketRuleMapper;
import com.koolearn.club.service.IRedPocketRuleService;
import com.koolearn.club.utils.JSONResult;
import com.koolearn.util.BeanUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 *
 */
public class RedPocketRuleServiceImpl implements IRedPocketRuleService {


    @Resource
    private RedPocketRuleMapper redPocketRuleMapper;

    /**
     * 增加红包规则
     *
     * @param redPocketRuleDTO
     * @param activityId
     * @return
     */
    @Override
    public JSONResult addRedPocketRule(RedPocketRuleDTO redPocketRuleDTO, Integer activityId) {
        JSONResult jsonResult = new JSONResult();
        PeRedPocketRule peRedPocketRule = new PeRedPocketRule();
        BeanUtils.copyProperties(peRedPocketRule, redPocketRuleDTO);
        peRedPocketRule.setActivityId(activityId);
        peRedPocketRule.setCreateTime(new Date());
        Integer result = redPocketRuleMapper.addRedPocketRule(peRedPocketRule);
        if (result > 0) {
            jsonResult.success(null);
        } else {
            jsonResult.fail(RedPocketErrorCodeEnum.RED_POCKET_ADD_FAIL.getCode(),
                    RedPocketErrorCodeEnum.RED_POCKET_ADD_FAIL.getMessage());
        }
        return jsonResult;
    }

    /**
     * 修改红包规则
     *
     * @param redPocketRuleDTO
     * @param activityId
     * @return
     */
    @Override
    public JSONResult editRedPocketRule(RedPocketRuleDTO redPocketRuleDTO, Integer activityId) {
        JSONResult jsonResult = new JSONResult();
        PeRedPocketRule peRedPocketRule = redPocketRuleMapper.findById(redPocketRuleDTO.getId());
        if (peRedPocketRule != null) {
            BeanUtils.copyProperties(peRedPocketRule, redPocketRuleDTO);
            Integer result = redPocketRuleMapper.editRedPocketRule(peRedPocketRule);
            if (result > 0) {
                jsonResult.success(null);
            } else {
                jsonResult.fail(RedPocketErrorCodeEnum.RED_POCKET_ADD_FAIL.getCode(), RedPocketErrorCodeEnum.RED_POCKET_ADD_FAIL.getMessage());
            }
        } else {
            jsonResult.fail(RedPocketErrorCodeEnum.RED_POCKET_NOT_FOUND.getCode(),
                    RedPocketErrorCodeEnum.RED_POCKET_NOT_FOUND.getMessage());

        }
        return jsonResult;
    }

    /**
     * 根据活动ID查找红包规则
     *
     * @param activityId
     * @return
     */
    @Override
    public JSONResult<RedPocketRuleDTO> findByActivityId(Integer activityId) {
        JSONResult<RedPocketRuleDTO> jsonResult = new JSONResult<>();
        PeRedPocketRule peRedPocketRule = redPocketRuleMapper.findByActivityId(activityId);
        if (peRedPocketRule != null) {
            RedPocketRuleDTO redPocketRuleDTO = new RedPocketRuleDTO();
            BeanUtils.copyProperties(redPocketRuleDTO, peRedPocketRule);
            jsonResult.success(redPocketRuleDTO);
        } else {
            jsonResult.fail(RedPocketErrorCodeEnum.RED_POCKET_NOT_FOUND.getCode(),
                    RedPocketErrorCodeEnum.RED_POCKET_NOT_FOUND.getMessage());

        }
        return jsonResult;
    }
}
