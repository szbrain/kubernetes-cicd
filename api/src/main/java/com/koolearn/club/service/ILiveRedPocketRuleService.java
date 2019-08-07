package com.koolearn.club.service;


import com.koolearn.club.dto.live.LiveRedPocketRuleDTO;
import com.koolearn.club.dto.live.LiveRedPocketRuleListRespDTO;
import com.koolearn.club.entity.PeLiveRedPocketRule;

import java.util.List;

public interface ILiveRedPocketRuleService {
    PeLiveRedPocketRule getById(int id);

    /**
     * 创建红包
     * @param liveRedPocketRuleDTO
     * @return
     */
    int create(LiveRedPocketRuleDTO liveRedPocketRuleDTO);

    /**
     * 开始发放红包
     * @param id
     * @return
     */
    int start(int id);

    /**
     * 结束发放红包
     * @param id
     * @return
     */
    int end(int id);

    /**
     * 通过任务ID，查询直播红包规则列表
     * @param taskId
     * @return
     */
    List<LiveRedPocketRuleListRespDTO> list(int taskId);
}
