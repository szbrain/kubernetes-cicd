package com.koolearn.club.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.koolearn.club.constants.*;
import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.dto.task.LiveTaskDTO;
import com.koolearn.club.dto.task.PostLiveTaskDTO;
import com.koolearn.club.dto.task.PostTaskDTO;
import com.koolearn.club.dto.task.TaskDTO;
import com.koolearn.club.entity.*;
import com.koolearn.club.exception.ClubServiceException;
import com.koolearn.club.mapper.*;
import com.koolearn.club.service.IStuMessageService;
import com.koolearn.club.service.ITaskService;
import com.koolearn.club.service.ITeachMessageService;
import com.koolearn.util.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class TaskServiceImpl implements ITaskService {

    @Resource
    private TaskMapper taskMapper;
    @Resource
    private ResourceMapper resourceMapper;
    @Resource
    private ITeachMessageService teachMessageService;
    @Resource
    private IStuMessageService stuMessageService;
    @Resource
    private LiveMapper liveMapper;
    @Resource
    private ClassMapper classMapper;
    @Resource
    private TeacherMapper teacherMapper;


    @Override
    public PeTask getById(int id) {
        return taskMapper.getById(id);
    }

    /**
     * 根据班级ID，查询最新的任务
     * @param classId
     * @return
     */
    @Override
    public TaskDTO getLastTaskByClassId(int classId) {
        //初始化返回对象
        TaskDTO taskDTO = new TaskDTO();
        List<PeResource> resourceList = Lists.newArrayList();
        //查询任务
        PeTask task = taskMapper.getLastTaskByClassId(classId);
        if(null == task){
            return null;
        }
        BeanUtils.copyProperties(taskDTO,task);
        String resourceIdsJson = task.getResourceIds();
        if(StringUtils.isNoneBlank(resourceIdsJson)){
            //查询任务对应资源
            List<Integer> resourceIdList = JSONObject.parseArray(resourceIdsJson, Integer.class);
            for(Integer resourceId : resourceIdList){
                PeResource resource = resourceMapper.getById(resourceId);
                if(null != resource){
                    resourceList.add(resource);
                }
            }
        }
        taskDTO.setResourceList(resourceList);
        return taskDTO;
    }


    /**
     * 根据班级ID，查询最新的任务
     * @param classId
     * @return
     */
    @Override
    public TaskDTO getLastTaskByClassIdOfV2(int classId) {
        //初始化返回对象
        TaskDTO taskDTO = new TaskDTO();
        List<PeResource> resourceList = Lists.newArrayList();
        //查询任务
        PeTask task = taskMapper.getLastTaskByClassIdOfV2(classId);
        if(null == task){
            return null;
        }
        BeanUtils.copyProperties(taskDTO,task);
        String resourceIdsJson = task.getResourceIds();
        if(StringUtils.isNoneBlank(resourceIdsJson)){
            //查询任务对应资源
            List<Integer> resourceIdList = JSONObject.parseArray(resourceIdsJson, Integer.class);
            for(Integer resourceId : resourceIdList){
                PeResource resource = resourceMapper.getById(resourceId);
                if(null != resource){
                    resourceList.add(resource);
                }
            }
        }
        taskDTO.setResourceList(resourceList);
        return taskDTO;
    }

    /**
     * 根据班级ID，查询任务
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    @Override
    public PageDTO<TaskDTO> listByClassId(int offset, int pageSize, int classId) {
        //初始化返回对象
        PageDTO<TaskDTO> pageDTO = new PageDTO<>();
        List<TaskDTO> taskDTOList = Lists.newArrayList();
        //查询任务列表
        List<PeTask> taskList = taskMapper.listByClassId(offset, pageSize, classId);
        for(PeTask task : taskList){
            TaskDTO taskDTO = new TaskDTO();
            List<PeResource> resourceList = Lists.newArrayList();
            BeanUtils.copyProperties(taskDTO,task);
            String resourceIdsJson = task.getResourceIds();
            if(StringUtils.isNoneBlank(resourceIdsJson)){
                //查询任务对应资源
                List<Integer> resourceIdList = JSONObject.parseArray(resourceIdsJson, Integer.class);
                for(Integer resourceId : resourceIdList){
                    PeResource resource = resourceMapper.getById(resourceId);
                    if(null != resource){
                        resourceList.add(resource);
                    }
                }
            }
            taskDTO.setResourceList(resourceList);
            taskDTOList.add(taskDTO);
        }
        pageDTO.setList(taskDTOList);
        pageDTO.setCount(taskMapper.countByClassId(classId));
        return pageDTO;
    }


    /**
     * 根据班级ID，查询任务
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    @Override
    public PageDTO<TaskDTO> listByClassIdOfV2(int offset, int pageSize, int classId) {
        //初始化返回对象
        PageDTO<TaskDTO> pageDTO = new PageDTO<>();
        List<TaskDTO> taskDTOList = Lists.newArrayList();
        //查询任务列表
        List<PeTask> taskList = taskMapper.listByClassIdOfV2(offset, pageSize, classId);
        for(PeTask task : taskList){
            TaskDTO taskDTO = new TaskDTO();
            List<PeResource> resourceList = Lists.newArrayList();
            BeanUtils.copyProperties(taskDTO,task);
            String resourceIdsJson = task.getResourceIds();
            if(StringUtils.isNoneBlank(resourceIdsJson)){
                //查询任务对应资源
                List<Integer> resourceIdList = JSONObject.parseArray(resourceIdsJson, Integer.class);
                for(Integer resourceId : resourceIdList){
                    PeResource resource = resourceMapper.getById(resourceId);
                    if(null != resource){
                        resourceList.add(resource);
                    }
                }
            }
            taskDTO.setResourceList(resourceList);
            taskDTOList.add(taskDTO);
        }
        pageDTO.setList(taskDTOList);
        pageDTO.setCount(taskMapper.countByClassIdOfV2(classId));
        return pageDTO;
    }

    @Override
    @Transactional
    public int postTask(PostTaskDTO postTaskDTO) {
        //插入任务内对应资源
        List<PeResource> resourceList = postTaskDTO.getResourceList();
        List<Integer> resourceIds = Lists.newArrayList();
        if(null != resourceList){
            for(PeResource resource : resourceList){
                resourceMapper.insert(resource);
                resourceIds.add(resource.getId());
            }
        }
        PeTask task = new PeTask();
        task.setClassId(postTaskDTO.getClassId());
        task.setCreateTime(new Date());
        task.setContent(postTaskDTO.getContent());
        task.setResourceIds(JSON.toJSONString(resourceIds));
        task.setStatus(TaskStatusEnum.POSTED.getCode());
        task.setType(TaskTypeEnum.NORMAL.getCode());

        //创建任务
        taskMapper.insert(task);
        final int taskId = task.getId();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //发消息给老师
                teachMessageService.sendMessage(TeachMessageTypeEnum.POST_TASK.getCode(), taskId);
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //发消息给学生
                stuMessageService.sendMessage(StuMessageTypeEnum.POST_TASK.getCode(), taskId);
            }
        });
        executorService.shutdown();
        return task.getId();
    }

    @Override
    public TaskDTO getDTOById(int id) {
        TaskDTO taskDTO = new TaskDTO();
        List<PeResource> resourceList = Lists.newArrayList();
        //查询任务
        PeTask task = taskMapper.getById(id);
        if(null == task){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_NOT_FOUND);
        }
        BeanUtils.copyProperties(taskDTO,task);
        String resourceIdsJson = task.getResourceIds();
        if(StringUtils.isNoneBlank(resourceIdsJson)){
            //查询任务对应资源
            List<Integer> resourceIdList = JSONObject.parseArray(resourceIdsJson, Integer.class);
            for(Integer resourceId : resourceIdList){
                PeResource resource = resourceMapper.getById(resourceId);
                if(null != resource){
                    resourceList.add(resource);
                }
            }
        }
        taskDTO.setResourceList(resourceList);
        return taskDTO;
    }

    @Override
    @Transactional
    public int editTask(PostTaskDTO postTaskDTO) {
        PeTask task = taskMapper.getById(postTaskDTO.getId());
        if(null == task){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_NOT_FOUND);
        }
        //删除资源表中原来的记录
        String resourceIdsJson = task.getResourceIds();
        if(StringUtils.isNoneBlank(resourceIdsJson)){
            List<Integer> resourceIds = JSON.parseArray(resourceIdsJson, Integer.class);
            if (resourceIds.size()>0){
                resourceMapper.batchDelete(resourceIds);
            }
        }
        //插入任务内对应资源
        List<PeResource> resourceList = postTaskDTO.getResourceList();
        List<Integer> resourceIds = Lists.newArrayList();
        if(null != resourceList){
            for(PeResource resource : resourceList){
                resourceMapper.insert(resource);
                resourceIds.add(resource.getId());
            }
        }
        task.setContent(postTaskDTO.getContent());
        task.setResourceIds(JSON.toJSONString(resourceIds));
        //更新任务
        taskMapper.update(task);
        return task.getId();
    }

    @Override
    @Transactional
    public int postLiveTask(PostLiveTaskDTO postLiveTaskDTO) {
        //插入任务内对应资源
        List<PeResource> resourceList = postLiveTaskDTO.getResourceList();
        List<Integer> resourceIds = Lists.newArrayList();
        if(null != resourceList){
            for(PeResource resource : resourceList){
                resourceMapper.insert(resource);
                resourceIds.add(resource.getId());
            }
        }
        PeTask task = new PeTask();
        task.setClassId(postLiveTaskDTO.getClassId());
        task.setCreateTime(new Date());
        task.setContent(postLiveTaskDTO.getContent());
        task.setResourceIds(JSON.toJSONString(resourceIds));
        task.setStatus(TaskStatusEnum.POSTED.getCode());
        task.setType(TaskTypeEnum.LIVE.getCode());
        //创建任务
        taskMapper.insert(task);
        //创建直播
        PeLive live = new PeLive();
        live.setTaskId(task.getId());
        live.setStatus(LiveStatusEnum.WAIT.getCode());
        live.setTitle(postLiveTaskDTO.getTitle());
        live.setIntroduction(postLiveTaskDTO.getIntroduction());
        live.setSpeaker(postLiveTaskDTO.getSpeaker());
        live.setCoverUrl(postLiveTaskDTO.getCoverUrl());
        live.setCreateTime(new Date());
        live.setOpenTime(postLiveTaskDTO.getOpenTime());
        live.setEndTime(postLiveTaskDTO.getEndTime());
        live.setPushUrl(postLiveTaskDTO.getPushUrl());
        live.setPlayHlsUrl(postLiveTaskDTO.getPlayHlsUrl());
        live.setPlayFlvUrl(postLiveTaskDTO.getPlayFlvUrl());
        live.setPlayRtmpUrl(postLiveTaskDTO.getPlayRtmpUrl());
        live.setImGroupId(postLiveTaskDTO.getGroupId());
        liveMapper.insert(live);
        final int taskId = task.getId();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //发消息给老师
                teachMessageService.sendMessage(TeachMessageTypeEnum.POST_TASK.getCode(), taskId);
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //发消息给学生
                stuMessageService.sendMessage(StuMessageTypeEnum.POST_TASK.getCode(), taskId);
            }
        });
        executorService.shutdown();
        return task.getId();
    }

    @Override
    public PageDTO<LiveTaskDTO> listLiveTaskByClassId(int offset, int pageSize, int classId) {
        //初始化返回对象
        PageDTO<LiveTaskDTO> pageDTO = new PageDTO<>();
        List<LiveTaskDTO> liveTaskDTOList = Lists.newArrayList();
        //查询直播列表
        List<PeLive> liveList = liveMapper.listByClassId(offset, pageSize, classId);
        for(PeLive live : liveList){
            LiveTaskDTO liveTaskDTO = new LiveTaskDTO();
            BeanUtils.copyProperties(liveTaskDTO,live);
            PeClass peClass = classMapper.getById(classId);
            if(null != peClass){
                int teachId = peClass.getTeachId();
                PeTeacher teacher = teacherMapper.getById(teachId);
                liveTaskDTO.setTeacher(teacher);
            }
            liveTaskDTOList.add(liveTaskDTO);
        }
        pageDTO.setList(liveTaskDTOList);
        pageDTO.setCount(liveMapper.countByClassId(classId));
        return pageDTO;
    }

    @Override
    @Transactional
    public int editLiveTask(PostLiveTaskDTO postLiveTaskDTO) {
        PeTask task = taskMapper.getById(postLiveTaskDTO.getId());
        if(null == task){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_NOT_FOUND);
        }
        //删除资源表中原来的记录
        String resourceIdsJson = task.getResourceIds();
        if(StringUtils.isNoneBlank(resourceIdsJson)){
            List<Integer> resourceIds = JSON.parseArray(resourceIdsJson, Integer.class);
            if (resourceIds.size()>0){
                resourceMapper.batchDelete(resourceIds);
            }
        }
        //插入任务内对应资源
        List<PeResource> resourceList = postLiveTaskDTO.getResourceList();
        List<Integer> resourceIds = Lists.newArrayList();
        if(null != resourceList){
            for(PeResource resource : resourceList){
                resourceMapper.insert(resource);
                resourceIds.add(resource.getId());
            }
        }
        task.setContent(postLiveTaskDTO.getContent());
        task.setResourceIds(JSON.toJSONString(resourceIds));
        //更新任务
        taskMapper.update(task);

        //更新直播
        PeLive live = liveMapper.getByTaskId(task.getId());
        if(null == live){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_LIVE_NOT_FOUND);
        }
        live.setCoverUrl(postLiveTaskDTO.getCoverUrl());
        live.setTitle(postLiveTaskDTO.getTitle());
        live.setSpeaker(postLiveTaskDTO.getSpeaker());
        live.setIntroduction(postLiveTaskDTO.getIntroduction());
        live.setOpenTime(postLiveTaskDTO.getOpenTime());
        live.setEndTime(postLiveTaskDTO.getEndTime());
        live.setPushUrl(postLiveTaskDTO.getPushUrl());
        live.setPlayHlsUrl(postLiveTaskDTO.getPlayHlsUrl());
        live.setPlayFlvUrl(postLiveTaskDTO.getPlayFlvUrl());
        live.setPlayRtmpUrl(postLiveTaskDTO.getPlayRtmpUrl());
        liveMapper.update(live);
        return task.getId();
    }

    @Override
    public Map getPushUrl(int id) {
        PeTask task = taskMapper.getById(id);
        if(null == task){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_NOT_FOUND);
        }
        if(task.getType() != TaskTypeEnum.LIVE.getCode()){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_TYPE_ERROR);
        }

        PeLive live = liveMapper.getByTaskId(id);
        if(null == live){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_LIVE_NOT_FOUND);
        }
        Map<String,String> retMap = Maps.newHashMap();
        retMap.put("pushUrl", live.getPushUrl());
        retMap.put("groupId", live.getImGroupId());
        return retMap;
    }

    @Override
    @Transactional
    public String endLive(int id) {
        PeTask task = taskMapper.getById(id);
        if(null == task){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_NOT_FOUND);
        }
        PeLive live = liveMapper.getByTaskId(id);
        if(null == live){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_LIVE_NOT_FOUND);
        }
        if(live.getStatus() != LiveStatusEnum.ONGOING.getCode()){
            throw new ClubServiceException(SystemErrorCode.BIZ_LIVE_STATUS_ERROR);
        }
        int count = liveMapper.updateStatus(live.getId(),LiveStatusEnum.FINISH.getCode());
        if(count > 0){
            return live.getImGroupId();
        }
        return null;
    }

    @Override
    public LiveTaskDTO getLiveTaskById(int id) {
        PeTask task = taskMapper.getById(id);
        if(null == task){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_NOT_FOUND);
        }
        if(task.getType() != TaskTypeEnum.LIVE.getCode()){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_TYPE_ERROR);
        }
        PeLive live = liveMapper.getByTaskId(id);
        if(null == live){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_LIVE_NOT_FOUND);
        }
        LiveTaskDTO liveTaskDTO = new LiveTaskDTO();
        BeanUtils.copyProperties(liveTaskDTO, live);
        liveTaskDTO.setTask(task);
        return liveTaskDTO;
    }

    @Override
    @Transactional
    public int startLive(int id) {
        PeTask task = taskMapper.getById(id);
        if(null == task){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_NOT_FOUND);
        }
        PeLive live = liveMapper.getByTaskId(id);
        if(null == live){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_LIVE_NOT_FOUND);
        }
        if(live.getStatus() != LiveStatusEnum.WAIT.getCode()){
            throw new ClubServiceException(SystemErrorCode.BIZ_LIVE_STATUS_ERROR);
        }
        return liveMapper.updateStatus(live.getId(),LiveStatusEnum.ONGOING.getCode());
    }

    @Override
    @Transactional
    public int updateOnlineCount(int id, int memberNum, Date date) {
        PeTask task = taskMapper.getById(id);
        if(null == task){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_NOT_FOUND);
        }
        PeLive live = liveMapper.getByTaskId(id);
        if(null == live){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_LIVE_NOT_FOUND);
        }
        return liveMapper.updateOnlineCount(live.getId(),memberNum, date);
    }

    @Override
    public int updatePlayUrl(int id, String playUrl) {
        PeTask task = taskMapper.getById(id);
        if(null == task){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_NOT_FOUND);
        }
        PeLive live = liveMapper.getByTaskId(id);
        if(null == live){
            throw new ClubServiceException(SystemErrorCode.BIZ_TASK_LIVE_NOT_FOUND);
        }
        return liveMapper.updatePlayUrl(id,playUrl);
    }
}
