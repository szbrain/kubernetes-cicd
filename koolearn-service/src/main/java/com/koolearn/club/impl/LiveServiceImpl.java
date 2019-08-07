package com.koolearn.club.impl;

import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.dto.live.LiveDTO;
import com.koolearn.club.entity.PeLive;
import com.koolearn.club.entity.PeTask;
import com.koolearn.club.exception.ClubServiceException;
import com.koolearn.club.mapper.LiveMapper;
import com.koolearn.club.mapper.TaskMapper;
import com.koolearn.club.service.ILiveService;
import com.koolearn.util.BeanUtils;

import javax.annotation.Resource;

/**
 * Created by lvyangjun on 2018/4/9.
 */
public class LiveServiceImpl implements ILiveService {

    @Resource
    private LiveMapper liveMapper;

    @Resource
    private TaskMapper taskMapper;

    @Override
    public LiveDTO getByTaskId(int taskId) {

        PeLive live = liveMapper.getByTaskId(taskId);
        LiveDTO liveDTO = null;
        if (live != null) {
            liveDTO = new LiveDTO();
            BeanUtils.copyProperties(liveDTO, live);
        } else {
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_LIVE_NOT_FOUND);
        }
        return liveDTO;
    }

    @Override
    public int updateNoticeByTaskId(int taskId, String notice) {
        PeTask task = taskMapper.getById(taskId);
        if(null == task){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_NOT_FOUND);
        }
        return liveMapper.updateNoticeByTaskId(taskId, notice);
    }
}
