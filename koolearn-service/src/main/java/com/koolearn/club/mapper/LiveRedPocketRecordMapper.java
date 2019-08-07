package com.koolearn.club.mapper;


import com.koolearn.club.dto.live.LiveRedPocketReqDTO;
import com.koolearn.club.entity.PeLiveRedPocketRecord;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@DAO
public interface LiveRedPocketRecordMapper {

    /**
     * 添加抽奖记录
     */
    int addRedPockrtRecord(PeLiveRedPocketRecord peLiveRedPocketRecord);

    /**
     * 已发放金额
     *
     * @param liveRedPocketId
     * @return
     */
    BigDecimal getTotalAmountByLiveRedPocketId(@Param("liveRedPocketId") Integer liveRedPocketId);

    /**
     * 领取人数
     *
     * @param liveRedPocketId
     * @return
     */
    int joinUserTotal(@Param("liveRedPocketId") Integer liveRedPocketId);

    /**
     * 该红包学生中奖记录
     * @param liveRedPocketReqDTO
     * @return
     */
    PeLiveRedPocketRecord getByStuIdAndLiveRedPocketId(LiveRedPocketReqDTO liveRedPocketReqDTO);


    /**
     *  根据红包ID 查询红包中奖纪录
     * @param liveRedPocketId
     * @return
     */
    List<PeLiveRedPocketRecord> listByLiveRedPocketId(int liveRedPocketId);
}
