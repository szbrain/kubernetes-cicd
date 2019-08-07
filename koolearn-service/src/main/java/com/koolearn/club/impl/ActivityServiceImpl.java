package com.koolearn.club.impl;

import com.alibaba.fastjson.JSON;
import com.koolearn.club.constants.ActivityStatusEnum;
import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.constants.activity.ActivityErrorCodeEnum;
import com.koolearn.club.constants.award.AwardItemErrorCodeEnum;
import com.koolearn.club.dto.activity.*;
import com.koolearn.club.dto.award.DrawAwardItemRespDTO;
import com.koolearn.club.dto.award.RuleDTO;
import com.koolearn.club.entity.PeActivity;
import com.koolearn.club.entity.PeDrawAwardItem;
import com.koolearn.club.entity.PeRedPocketCode;
import com.koolearn.club.exception.ClubServiceException;
import com.koolearn.club.mapper.*;
import com.koolearn.club.service.IActivityService;
import com.koolearn.club.service.IDrawAwardService;
import com.koolearn.club.service.IRedPocketRuleService;
import com.koolearn.club.utils.JSONPageResult;
import com.koolearn.club.utils.JSONResult;
import com.koolearn.util.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lvyangjun on 2018/4/23.
 */
public class ActivityServiceImpl implements IActivityService {


    @Resource
    ActivityMapper activityMapper;

    @Resource
    IDrawAwardService drawAwardService;

    @Resource
    DrawAwardItemMapper drawAwardItemMapper;

    @Resource
    DrawRecordMapper drawRecordMapper;

    @Resource
    IRedPocketRuleService redPocketRuleService;

    @Resource
    RedPocketRecordMapper redPocketRecordMapper;

    @Resource
    RedPocketCodeMapper redPocketCodeMapper;


    /**
     * 增加活动
     *
     * @param activityReqDTO
     * @return
     */
    @Override
    @Transactional
    public JSONResult addActivity(ActivityReqDTO activityReqDTO) {
        JSONResult jsonResult = new JSONResult();
        PeActivity activity = new PeActivity();
        BeanUtils.copyProperties(activity, activityReqDTO);
        activity.setCreateTime(new Date());
        activity.setRule(JSON.toJSONString(activityReqDTO.getRuleDTO()));
        activity.setStatus(ActivityStatusEnum.WAIT.getCode());
        if (activityReqDTO.getType() == 1) {//打卡抽奖
            if (activityReqDTO.getDrawAwardItemReqDTOList() != null && activityReqDTO.getDrawAwardItemReqDTOList().size() > 0) {
                activityMapper.insert(activity);
                boolean result = drawAwardService.addAwardItem(activityReqDTO.getDrawAwardItemReqDTOList(), activity.getId());
                if (result) {
                    jsonResult.success(null);
                } else {
                    jsonResult.fail(ActivityErrorCodeEnum.ACTIVITY_SAVE_FAIL.getCode(), ActivityErrorCodeEnum.ACTIVITY_SAVE_FAIL.getMessage());
                }
            } else {
                jsonResult.fail(ActivityErrorCodeEnum.ACTIVITY_NO_AWARD_ITEM.getCode(), ActivityErrorCodeEnum.ACTIVITY_NO_AWARD_ITEM.getMessage());
            }
        } else if (activityReqDTO.getType() == 4) {//抽奖红包

            //红包创建码校验
            if (StringUtils.isNotEmpty(activityReqDTO.getCode())) {
                PeRedPocketCode peRedPocketCode = redPocketCodeMapper.checkCode(activityReqDTO.getCode());
                if (peRedPocketCode != null) {
                    if (peRedPocketCode.getType() == 0 && peRedPocketCode.getStatus() == 0) {
                        peRedPocketCode.setStatus((short) 1);
                        redPocketCodeMapper.updateStatus(peRedPocketCode);
                    }
                    activityMapper.insert(activity);
                    jsonResult = redPocketRuleService.addRedPocketRule(activityReqDTO.getRedPocketRuleDTO(), activity.getId());
                } else {
                    jsonResult.fail(ActivityErrorCodeEnum.ACTIVITY_RED_POCKET_CODE_ERROR.getCode(), ActivityErrorCodeEnum.ACTIVITY_RED_POCKET_CODE_ERROR.getMessage());
                }
            } else {
                jsonResult.fail(ActivityErrorCodeEnum.ACTIVITY_NO_RED_POCKET_CODE.getCode(), ActivityErrorCodeEnum.ACTIVITY_NO_RED_POCKET_CODE.getMessage());
            }
        }
        return jsonResult;
    }

    /**
     * 活动列表
     *
     * @param activityListReqDTO
     * @return
     */
    @Override
    public JSONPageResult<List<ActivityRespDTO>> activityList(ActivityListReqDTO activityListReqDTO) {

        JSONPageResult<List<ActivityRespDTO>> listJSONPageResult = new JSONPageResult<List<ActivityRespDTO>>();
        List<PeActivity> peActivityList = activityMapper.activityList(activityListReqDTO);
        if (peActivityList != null && peActivityList.size() > 0) {
            List<ActivityRespDTO> activityRespDTOList = new ArrayList<>();
            for (PeActivity peActivity : peActivityList) {
                ActivityRespDTO activityRespDTO = new ActivityRespDTO();
                //activityRespDTO.setJoinUserTotal(drawRecordMapper.joinUserTotal(peActivity.getId()));
                //activityRespDTO.setJoinTotal(drawRecordMapper.joinTotal(peActivity.getId()));
                BeanUtils.copyProperties(activityRespDTO, peActivity);
                activityRespDTOList.add(activityRespDTO);
            }
            listJSONPageResult.success(activityRespDTOList);
        } else {
            listJSONPageResult.success(new ArrayList<ActivityRespDTO>());
        }
        listJSONPageResult.setPageSize(activityListReqDTO.getPageSize());
        listJSONPageResult.setPageNum(activityListReqDTO.getOffset());
        int count = activityMapper.listCount(activityListReqDTO);
        if (count % activityListReqDTO.getPageSize() == 0) {
            listJSONPageResult.setPages(count / activityListReqDTO.getPageSize());
        } else {
            listJSONPageResult.setPages((count / activityListReqDTO.getPageSize()) + 1);
        }
        listJSONPageResult.setTotal(count);
        return listJSONPageResult;
    }

    /**
     * 活动详情
     *
     * @param activityDetailReqDTO
     * @return
     */
    @Override
    public JSONResult<ActivityDetailRespDTO> activityDetail(ActivityDetailReqDTO activityDetailReqDTO) {
        JSONResult<ActivityDetailRespDTO> jsonResult = new JSONResult<>();
        ActivityDetailRespDTO activityDetailRespDTO = new ActivityDetailRespDTO();
        PeActivity activity = activityMapper.activityDetail(activityDetailReqDTO);
        if (activity != null) {
            BeanUtils.copyProperties(activityDetailRespDTO, activity);
            RuleDTO ruleDTO = JSON.parseObject(activity.getRule(), RuleDTO.class);
            activityDetailRespDTO.setRuleDTO(ruleDTO);
            if (activity.getType() == 1) {
                List<DrawAwardItemRespDTO> drawAwardItemRespDTOList = drawAwardService.getListByActivityId(activityDetailReqDTO.getActivityId());
                activityDetailRespDTO.setDrawAwardItemRespDTOList(drawAwardItemRespDTOList);
                activityDetailRespDTO.setJoinUserTotal(drawRecordMapper.joinUserTotal(activity.getId()));
                activityDetailRespDTO.setJoinTotal(drawRecordMapper.joinTotal(activity.getId()));
                activityDetailRespDTO.setTodayJoinUserTotal(drawRecordMapper.todayJoinUserTotal(activity.getId()));
                activityDetailRespDTO.setTodayJoinTotal(drawRecordMapper.todayJoinTotal(activity.getId()));

                jsonResult.success(activityDetailRespDTO);
            } else if (activity.getType() == 4) {
                JSONResult<RedPocketRuleDTO> redPocketRuleDTOJSONResult = redPocketRuleService.findByActivityId(activity.getId());
                if (redPocketRuleDTOJSONResult.getCode() == JSONResult.SUCCESS) {
                    activityDetailRespDTO.setRedPocketRuleDTO(redPocketRuleDTOJSONResult.getData());
                    activityDetailRespDTO.setJoinUserTotal(redPocketRecordMapper.joinUserTotal(activity.getId()));
                    activityDetailRespDTO.setAllAmount(redPocketRecordMapper.getAllAmountByActivityId(activity.getId()));
                    activityDetailRespDTO.setTodayJoinUserTotal(redPocketRecordMapper.todayJoinUserTotal(activity.getId()));
                    activityDetailRespDTO.setTodayAmount(redPocketRecordMapper.getEveryDayAmountByActivityId(activity.getId()));
                    jsonResult.success(activityDetailRespDTO);
                } else {
                    jsonResult.fail(redPocketRuleDTOJSONResult.getCode(), redPocketRuleDTOJSONResult.getMsg());
                }
            }
        } else {
            jsonResult.fail(ActivityErrorCodeEnum.ACTIVITY_NOT_FOUND.getCode(), ActivityErrorCodeEnum.ACTIVITY_NOT_FOUND.getMessage());
        }
        return jsonResult;
    }

    /**
     * 编辑活动
     *
     * @param activityEditReqDTO
     * @return
     */
    @Override
    public JSONResult editActivity(ActivityEditReqDTO activityEditReqDTO) {
        JSONResult jsonResult = new JSONResult();
        if (activityEditReqDTO.getRuleDTO() != null) {
            activityEditReqDTO.setRule(JSON.toJSONString(activityEditReqDTO.getRuleDTO()));
        }
        activityMapper.editActivity(activityEditReqDTO);
        //if (activityEditReqDTO.getType() == 1) {//打卡抽奖
            if (activityEditReqDTO.getDrawAwardItemReqDTOList() != null && activityEditReqDTO.getDrawAwardItemReqDTOList().size() > 0) {
                boolean result = drawAwardService.editAwardItem(activityEditReqDTO.getDrawAwardItemReqDTOList(), activityEditReqDTO.getActivityId());
                if (result) {
                    jsonResult.success(null);
                } else {
                    jsonResult.fail(AwardItemErrorCodeEnum.AWARD_ITEM_EDIT_FAIL.getCode(),
                            AwardItemErrorCodeEnum.AWARD_ITEM_EDIT_FAIL.getMessage());
                }
            }
        //} else if (activityEditReqDTO.getType() == 4) {
          //  jsonResult = redPocketRuleService.editRedPocketRule(activityEditReqDTO.getRedPocketRuleDTO(), activityEditReqDTO.getActivityId());

        //}
        return jsonResult;
    }

    /**
     * 删除奖项
     *
     * @param id
     * @return
     */
    @Override
    public JSONResult deleteAwardItem(Integer id) {

        PeDrawAwardItem peDrawAwardItem = drawAwardItemMapper.queryAwardItemById(id);
        if (peDrawAwardItem != null) {
            peDrawAwardItem = new PeDrawAwardItem();
            peDrawAwardItem.setId(id);
            peDrawAwardItem.setStatus(0);
            boolean result = drawAwardItemMapper.editAwardItem(peDrawAwardItem);
            if (result) {
                return new JSONResult<>().success(null);
            } else {
                return new JSONResult().fail(AwardItemErrorCodeEnum.AWARD_ITEM_DELETE_FAIL.getCode(),
                        AwardItemErrorCodeEnum.AWARD_ITEM_DELETE_FAIL.getMessage());
            }
        } else {
            return new JSONResult().fail(AwardItemErrorCodeEnum.AWARD_ITEM_NOT_FOUND.getCode(),
                    AwardItemErrorCodeEnum.AWARD_ITEM_NOT_FOUND.getMessage());
        }
    }


    /**
     * 开始活动
     *
     * @param id
     * @return
     */
    @Override
    public int startActivity(Integer id) {
        PeActivity activity = activityMapper.getById(id);
        if (null == activity) {
            throw new ClubServiceException(SystemErrorCode.BIZ_ACTIVITY_NOT_FOUND);
        }
        if (activity.getStatus() != ActivityStatusEnum.WAIT.getCode()) {
            throw new ClubServiceException(SystemErrorCode.BIZ_ACTIVITY_STATUS_ERROR);
        }
        Integer classId = Integer.parseInt(activity.getClassId());

        List<PeActivity> activityList = activityMapper.listByClassId(classId);
        boolean flag = false;
        for (PeActivity activity1 : activityList) {
            if (activity1.getStatus() == ActivityStatusEnum.ONGOING.getCode()) {
                flag = true;
                break;
            }
        }
        if (flag) {
            throw new ClubServiceException(SystemErrorCode.BIZ_HAS_ONGOING_ACTIVITY);
        }
        return activityMapper.updateStatus(id, ActivityStatusEnum.ONGOING.getCode());
    }

    /**
     * 结束活动
     *
     * @param id
     * @return
     */
    @Override
    public int finishActivity(Integer id) {
        PeActivity activity = activityMapper.getById(id);
        if (null == activity) {
            throw new ClubServiceException(SystemErrorCode.BIZ_ACTIVITY_NOT_FOUND);
        }
        if (activity.getStatus() != ActivityStatusEnum.ONGOING.getCode()) {
            throw new ClubServiceException(SystemErrorCode.BIZ_ACTIVITY_STATUS_ERROR);
        }
        return activityMapper.updateStatus(id, ActivityStatusEnum.FINISHED.getCode());
    }
}
