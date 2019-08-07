package com.koolearn.club.mapper;


import com.koolearn.club.dto.award.AppAwardRecordListReqDTO;
import com.koolearn.club.dto.award.AwardRecordListParamDTO;
import com.koolearn.club.dto.award.CheckAwardReqDTO;
import com.koolearn.club.dto.award.UpdateAwardRecordReqDTO;
import com.koolearn.club.entity.PeDrawRecord;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@DAO
public interface DrawRecordMapper {

	/**
	 * 添加抽奖记录
	 * */
	int addActDrawRecord(PeDrawRecord peDrawRecord);

	/**
	 * 查询中奖名单列表
	 * @param awardRecordListParamDTO
	 * @return
	 */
    List<PeDrawRecord> listForWebapp(AwardRecordListParamDTO awardRecordListParamDTO);

	int countForWebapp(AwardRecordListParamDTO awardRecordListParamDTO);

    /**
     * 学生中奖数据
     *
     * @param checkAwardReqDTO
     * @return
     */
    List<PeDrawRecord> getPeDrawRecordListByUserId(CheckAwardReqDTO checkAwardReqDTO);


	int updateAwardRecord(UpdateAwardRecordReqDTO updateAwardReqDTO);


    PeDrawRecord getPeDrawRecordById(UpdateAwardRecordReqDTO updateAwardReqDTO);

	/**
	 * 查询所有中奖名单列表
	 * @param awardRecordListParamDTO
	 * @return
	 */
    List<PeDrawRecord> listAllForWebapp(AwardRecordListParamDTO awardRecordListParamDTO);

    List<PeDrawRecord> awardRecordList(AppAwardRecordListReqDTO appAwardRecordListReqDTO);

    int awardRecordListCount(AppAwardRecordListReqDTO appAwardRecordListReqDTO);

    int totalNumByStuIdAndItemId(@Param("stuId") Integer stuId, @Param("awardItemId") Integer awardItemId);

    int totalNumByItemId(@Param("awardItemId") Integer awardItemId);

    int joinUserTotal(@Param("activityId") Integer activityId);

	int joinTotal(@Param("activityId") Integer activityId);

	int todayJoinUserTotal(@Param("activityId") Integer activityId);

	int todayJoinTotal(@Param("activityId") Integer activityId);


}
