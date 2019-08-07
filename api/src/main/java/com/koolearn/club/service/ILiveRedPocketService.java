package com.koolearn.club.service;


import com.koolearn.club.dto.award.RedPocketRecordDTO;
import com.koolearn.club.dto.live.*;
import com.koolearn.club.utils.JSONPageResult;
import com.koolearn.club.utils.JSONResult;

import java.util.List;

public interface ILiveRedPocketService {


    /**
     * 开红包
     *
     * @param liveRedPocketReqDTO
     * @return
     */
    JSONResult<LiveRedPocketRecordDTO> open(LiveRedPocketReqDTO liveRedPocketReqDTO);


    /**
     * 红包次数检查
     *
     * @param liveRedPocketReqDTO
     * @return
     */
    JSONResult<CheckOpenRespDTO> checkOpen(LiveRedPocketReqDTO liveRedPocketReqDTO);

    /**
     * 直播中奖列表
     * @param liveRedPocketId
     * @return
     */
    List<LiveRedPocketRecordRespDTO> redPocketRecordList(int liveRedPocketId);


}
