package com.koolearn.club.service;


import com.koolearn.club.dto.award.*;
import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.utils.JSONPageResult;
import com.koolearn.club.utils.JSONResult;

import java.util.List;

public interface IDrawAwardService {


    JSONResult doDraw(String sid,int activityId,List<Integer> learingTypeList);


    /**
     * 抽奖次数检查
     * @param checkAwardReqDTO
     * @return
     */
    JSONResult<CheckAwardRespDTO> checkAward(CheckAwardReqDTO checkAwardReqDTO);

    /**
     * 添加奖品
     * @param awardReqDTO
     * @return
     */
    JSONResult addAward( AwardReqDTO awardReqDTO);

    /**
     * 编辑奖品
     * @param awardEditReqDTO
     * @return
     */
    JSONResult editAward(AwardEditReqDTO awardEditReqDTO);

    /**
     * 添加奖项
     * @param drawAwardItemReqDTOList
     * @return
     */
    boolean addAwardItem(List<DrawAwardItemReqDTO> drawAwardItemReqDTOList,Integer activityId );

    /**
     * 编辑奖项
     * @param drawAwardItemReqDTOList
     * @return
     */
    boolean editAwardItem(List<DrawAwardItemReqDTO> drawAwardItemReqDTOList,Integer activityId);

    /**
     * 根据活动ID获取奖项列表
     * @param activityId
     * @return
     */
    List<DrawAwardItemRespDTO> getListByActivityId(int activityId);


    JSONPageResult<List<AwardListRespDTO>> getAwardListByTeach(AwardListReqDTO awardListReqDTO);


    JSONResult updateAwardRecord(UpdateAwardRecordReqDTO updateAwardReqDTO);


    PageDTO<AppAwardRecordListRespDTO> awardRecordList(AppAwardRecordListReqDTO appAwardRecordListReqDTO);


    JSONResult<List<AwardListRespDTO>> simpleActivityList(Integer teachId);



}
