package com.koolearn.club.impl;

import com.google.common.collect.Lists;
import com.koolearn.club.constants.LiveRedPocketRuleEnum;
import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.constants.TaskTypeEnum;
import com.koolearn.club.dto.live.LiveRedPocketRuleDTO;
import com.koolearn.club.dto.live.LiveRedPocketRuleListRespDTO;
import com.koolearn.club.entity.PeLiveRedPocketRule;
import com.koolearn.club.entity.PeTask;
import com.koolearn.club.exception.ClubServiceException;
import com.koolearn.club.mapper.LiveRedPocketRecordMapper;
import com.koolearn.club.mapper.LiveRedPocketRuleMapper;
import com.koolearn.club.mapper.TaskMapper;
import com.koolearn.club.service.ILiveRedPocketRuleService;
import com.koolearn.util.BeanUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class LiveRedPocketRuleServiceImpl implements ILiveRedPocketRuleService {


    @Resource
    private LiveRedPocketRuleMapper liveRedPocketRuleMapper;
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private LiveRedPocketRecordMapper liveRedPocketRecordMapper;


    @Override
    public PeLiveRedPocketRule getById(int id) {
        return liveRedPocketRuleMapper.getById(id);
    }

    @Override
    public int create(LiveRedPocketRuleDTO liveRedPocketRuleDTO) {
        Integer taskId = liveRedPocketRuleDTO.getTaskId();
        PeTask task = taskMapper.getById(taskId);
        if(null == task){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_NOT_FOUND);
        }
        if(task.getType() != TaskTypeEnum.LIVE.getCode()){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_TYPE_ERROR);
        }
        PeLiveRedPocketRule liveRedPocketRule = new PeLiveRedPocketRule();
        BeanUtils.copyProperties(liveRedPocketRule, liveRedPocketRuleDTO);
        liveRedPocketRule.setCreateTime(new Date());
        liveRedPocketRule.setStatus(LiveRedPocketRuleEnum.SAVED.getCode());
        return liveRedPocketRuleMapper.insert(liveRedPocketRule);
    }

    @Override
    public int start(int id) {
        PeLiveRedPocketRule liveRedPocketRule = getById(id);
        if(null == liveRedPocketRule){
            throw new ClubServiceException(SystemErrorCode.BIZ_LIVE_RED_POCKET_RULE_NOT_FOUND);
        }
        if(liveRedPocketRule.getStatus() != LiveRedPocketRuleEnum.SAVED.getCode()){
            throw new ClubServiceException(SystemErrorCode.BIZ_LIVE_RED_POCKET_RULE_STATUS_ERROR);
        }
        PeTask task = taskMapper.getById(liveRedPocketRule.getTaskId());
        if(null == task){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_NOT_FOUND);
        }
        PeLiveRedPocketRule liveRedPocketRuleDb = liveRedPocketRuleMapper.getByTaskIdAndStatus(task.getId(), LiveRedPocketRuleEnum.ONGOING.getCode());
        if(null != liveRedPocketRuleDb){
            throw new ClubServiceException(SystemErrorCode.BIZ_EXIST_ONGOING_LIVE_RED_POCKET_RULE);
        }
        return liveRedPocketRuleMapper.updateStatus(LiveRedPocketRuleEnum.ONGOING.getCode(), id);
    }

    @Override
    public int end(int id) {
        PeLiveRedPocketRule liveRedPocketRule = getById(id);
        if(null == liveRedPocketRule){
            throw new ClubServiceException(SystemErrorCode.BIZ_LIVE_RED_POCKET_RULE_NOT_FOUND);
        }
        if(liveRedPocketRule.getStatus() != LiveRedPocketRuleEnum.ONGOING.getCode()){
            throw new ClubServiceException(SystemErrorCode.BIZ_LIVE_RED_POCKET_RULE_STATUS_ERROR);
        }
        return liveRedPocketRuleMapper.updateStatus(LiveRedPocketRuleEnum.END.getCode(), id);
    }

    @Override
    public List<LiveRedPocketRuleListRespDTO> list(int taskId) {
        List<LiveRedPocketRuleListRespDTO> liveRedPocketRuleListRespDTOList = Lists.newArrayList();
        PeTask task = taskMapper.getById(taskId);
        if(null == task){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_NOT_FOUND);
        }
        List<PeLiveRedPocketRule> liveRedPocketRuleList = liveRedPocketRuleMapper.listByTaskId(taskId);
        for(PeLiveRedPocketRule liveRedPocketRule : liveRedPocketRuleList){
            LiveRedPocketRuleListRespDTO liveRedPocketRuleListRespDTO = new LiveRedPocketRuleListRespDTO();
            BeanUtils.copyProperties(liveRedPocketRuleListRespDTO, liveRedPocketRule);
            int count = liveRedPocketRecordMapper.joinUserTotal(liveRedPocketRule.getId());
            liveRedPocketRuleListRespDTO.setIssuedCount(count);
            BigDecimal amount = liveRedPocketRecordMapper.getTotalAmountByLiveRedPocketId(liveRedPocketRule.getId());
            if(null != amount){
                liveRedPocketRuleListRespDTO.setIssuedAmount(amount);
            }else{
                liveRedPocketRuleListRespDTO.setIssuedAmount(BigDecimal.ZERO);
            }
            liveRedPocketRuleListRespDTOList.add(liveRedPocketRuleListRespDTO);
        }
        return liveRedPocketRuleListRespDTOList;
    }
}
