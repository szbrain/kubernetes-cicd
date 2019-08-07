package com.koolearn.club.mapper;


import com.koolearn.club.dto.activity.RedPocketRecordListReqDTO;
import com.koolearn.club.dto.award.CheckAwardReqDTO;
import com.koolearn.club.entity.PeRedPocketRecord;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@DAO
public interface RedPocketRecordMapper {

    /**
     * 添加抽奖记录
     */
    int addRedPockrtRecord(PeRedPocketRecord peRedPocketRecord);

    /**
     * 每天中奖金额之和
     *
     * @param activityId
     * @return
     */
    BigDecimal getEveryDayAmountByActivityId(@Param("activityId") Integer activityId);

    /**
     * 所有中奖金额之和
     *
     * @param activityId
     * @return
     */
    BigDecimal getAllAmountByActivityId(@Param("activityId") Integer activityId);

    /**
     * 学生当天抽奖数据
     * @param checkAwardReqDTO
     * @return
     */
    List<PeRedPocketRecord> getListByUserId(CheckAwardReqDTO checkAwardReqDTO);

    /**
     * 活动参与人数
     * @param activityId
     * @return
     */
    int joinUserTotal(@Param("activityId") Integer activityId);

    /**
     * 活动参与次数
     * @param activityId
     * @return
     */
    int joinTotal(@Param("activityId") Integer activityId);


    /**
     * 当日活动参与人数
     * @param activityId
     * @return
     */
    int todayJoinUserTotal(@Param("activityId") Integer activityId);

    /**
     * 当日活动参与次数
     * @param activityId
     * @return
     */
    int todayJoinTotal(@Param("activityId") Integer activityId);

    /**
     * 红包中奖列表
     * @param redPocketRecordListReqDTO
     * @return
     */
    List<PeRedPocketRecord> redPocketRecordList(RedPocketRecordListReqDTO redPocketRecordListReqDTO);

    /**
     * 红包中奖列表总数
     * @param redPocketRecordListReqDTO
     * @return
     */
    Integer redPocketRecordListCount(RedPocketRecordListReqDTO redPocketRecordListReqDTO);

    /**
     * 导出红包中奖列表
     * @param redPocketRecordListReqDTO
     * @return
     */
    List<PeRedPocketRecord> export(RedPocketRecordListReqDTO redPocketRecordListReqDTO);


}
