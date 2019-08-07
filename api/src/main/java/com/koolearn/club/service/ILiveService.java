package com.koolearn.club.service;

import com.koolearn.club.dto.live.LiveDTO;

/**
 * Created by lvyangjun on 2018/4/9.
 */

public interface ILiveService {

    LiveDTO getByTaskId(int taskId);

    /**
     * 根据任务ID 更新直播通知
     * @param taskId
     * @param notice
     * @return
     */
    int updateNoticeByTaskId(int taskId, String notice);
}
