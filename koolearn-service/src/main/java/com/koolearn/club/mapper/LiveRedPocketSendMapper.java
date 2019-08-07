package com.koolearn.club.mapper;


import com.koolearn.club.dto.live.LiveRedPocketReqDTO;
import com.koolearn.club.entity.PeLiveRedPocketSend;
import com.koolearn.framework.mybatis.annotation.DAO;

@DAO
public interface LiveRedPocketSendMapper {

    /**
     * 添加抽奖记录
     */
    int addRedPockrtRecord(PeLiveRedPocketSend peLiveRedPocketSend);


    /**
     * 该红包学生中奖记录
     *
     * @param liveRedPocketReqDTO
     * @return
     */
    PeLiveRedPocketSend getByStuIdAndLiveRedPocketId(LiveRedPocketReqDTO liveRedPocketReqDTO);


}
