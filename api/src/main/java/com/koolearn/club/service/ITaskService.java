package com.koolearn.club.service;

import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.dto.task.LiveTaskDTO;
import com.koolearn.club.dto.task.PostLiveTaskDTO;
import com.koolearn.club.dto.task.PostTaskDTO;
import com.koolearn.club.dto.task.TaskDTO;
import com.koolearn.club.entity.PeTask;

import java.util.Date;
import java.util.Map;

/**
 * Created by lilong01 on 2018/2/27.
 */
public interface ITaskService {

    PeTask getById(int id);

    /**
     * 根据班级ID，查询最新任务
     * @param classId
     * @return
     */
    TaskDTO getLastTaskByClassId(int classId);

    TaskDTO getLastTaskByClassIdOfV2(int classId);

    /**
     * 根据班级ID，查询任务
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    PageDTO<TaskDTO> listByClassId(int offset, int pageSize, int classId);

    /**
     * 根据班级ID，查询任务
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    PageDTO<TaskDTO> listByClassIdOfV2(int offset, int pageSize, int classId);

    /**
     * 发布任务
     * @param postTaskDTO
     * @return
     */
    int postTask(PostTaskDTO postTaskDTO);

    /**
     * 通过ID查询DTO
     * @param id
     * @return
     */
    TaskDTO getDTOById(int id);

    /**
     * 编辑任务
     * @param postTaskDTO
     * @return
     */
    int editTask(PostTaskDTO postTaskDTO);

    /**
     * 发布直播任务
     * @param postLiveTaskDTO
     * @return
     */
    int postLiveTask(PostLiveTaskDTO postLiveTaskDTO);

    /**
     * 根据班级ID，查询直播任务
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    PageDTO<LiveTaskDTO> listLiveTaskByClassId(int offset, int pageSize, int classId);

    /**
     * 编辑直播任务
     * @param postLiveTaskDTO
     * @return
     */
    int editLiveTask(PostLiveTaskDTO postLiveTaskDTO);

    /**
     * 获取推流地址
     * @param id
     * @return
     */
    Map getPushUrl(int id);

    /**
     * 结束直播
     * @param id
     * @return
     */
    String endLive(int id);

    /**
     *  查询直播任务详情
     * @param id
     * @return
     */
    LiveTaskDTO getLiveTaskById(int id);

    /**
     * 开始直播
     * @param id
     * @return
     */
    int startLive(int id);

    /**
     * 更新直播任务在线人数
     * @param id
     * @param memberNum
     * @param date
     * @return
     */
    int updateOnlineCount(int id, int memberNum, Date date);

    /**
     *  更新播放地址
     * @param id
     * @param playUrl
     * @return
     */
    int updatePlayUrl(int id, String playUrl);
}
