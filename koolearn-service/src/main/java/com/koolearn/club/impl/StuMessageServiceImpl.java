package com.koolearn.club.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.koolearn.club.constants.StuMessageTypeEnum;
import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.dto.learning.LearningDTO;
import com.koolearn.club.dto.message.StuMessageDTO;
import com.koolearn.club.dto.task.TaskDTO;
import com.koolearn.club.entity.*;
import com.koolearn.club.exception.ClubServiceException;
import com.koolearn.club.mapper.*;
import com.koolearn.club.service.IStuMessageService;
import com.koolearn.util.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class StuMessageServiceImpl implements IStuMessageService {

    @Resource
    private StuMessageMapper stuMessageMapper;

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private ClassNoticeMapper classNoticeMapper;

    @Resource
    private LearningMapper learningMapper;

    @Resource
    private ResourceMapper resourceMapper;

    @Resource
    private LearningCommentMapper learningCommentMapper;

    @Resource
    private ClassMapper classMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private ClassStudentMapper classStudentMapper;

    @Override
    public PeStuMessage getById(int id) {
        return stuMessageMapper.getById(id);
    }

    @Override
    public PageDTO<StuMessageDTO> listMessageForStu(int offset, int pageSize, int stuId, int classId) {
        //初始化返回对象
        PageDTO<StuMessageDTO> pageDTO = new PageDTO<>();
        List<StuMessageDTO> messageDTOList = Lists.newArrayList();
        //查询学生通知列表
        List<PeStuMessage> messageList = stuMessageMapper.listMessageForStu(offset, pageSize, stuId,classId);
        for (PeStuMessage message : messageList) {
            StuMessageDTO stuMessageDTO = wrapMessageDTO(message);
            if(null != stuMessageDTO){
                messageDTOList.add(wrapMessageDTO(message));
            }
        }
        pageDTO.setList(messageDTOList);
        pageDTO.setCount(stuMessageMapper.countMessageForStu(stuId));
        return pageDTO;
    }

    @Override
    public PageDTO<StuMessageDTO> listMessageForTeach(int offset, int pageSize, int classId) {
        //初始化返回对象
        PageDTO<StuMessageDTO> pageDTO = new PageDTO<>();
        List<StuMessageDTO> messageDTOList = Lists.newArrayList();
        //查询老师通知列表
        List<PeStuMessage> messageList = stuMessageMapper.listMessageForTeach(offset, pageSize, classId);
        for (PeStuMessage message : messageList) {
            StuMessageDTO stuMessageDTO = wrapMessageDTO(message);
            if(null != stuMessageDTO){
                messageDTOList.add(stuMessageDTO);
            }
        }
        pageDTO.setList(messageDTOList);
        pageDTO.setCount(stuMessageMapper.countMessageForTeach(classId));
        return pageDTO;
    }

    @Override
    @Transactional
    public int sendMessage(short type, int businessId) {
        int count = 0;

        if(type == StuMessageTypeEnum.POST_TASK.getCode()){ //发布新的任务
            PeTask task = taskMapper.getById(businessId);
            if(null != task){
                PeClass peClass = classMapper.getById(task.getClassId());
                if(null != peClass){
                    //找到班级下所有的学生,批量发送通知
                    List<PrClassStudent> classStudentList = classStudentMapper.listByClassId(peClass.getId());
                    if(null != classStudentList && classStudentList.size() > 0){
                        List<PeStuMessage> stuMessageList = Lists.newArrayList();
                        for(PrClassStudent classStudent : classStudentList){
                            PeStuMessage stuMessage = new PeStuMessage();
                            stuMessage.setCreateTime(new Date());
                            stuMessage.setBusinessId(businessId);
                            stuMessage.setType(type);
                            stuMessage.setClassId(peClass.getId());
                            stuMessage.setStuId(classStudent.getStuId());
                            stuMessageList.add(stuMessage);
                        }
                        count += stuMessageMapper.batchInsert(stuMessageList);
                    }
                }
            }
        }
        if(type == StuMessageTypeEnum.POST_NOTICE.getCode()){ //发布班级公告
            PeClassNotice classNotice = classNoticeMapper.getById(businessId);
            if(null != classNotice){
                PeClass peClass = classMapper.getById(classNotice.getClassId());
                if(null != peClass){
                    //找到班级下所有的学生,批量发送通知
                    List<PrClassStudent> classStudentList = classStudentMapper.listByClassId(peClass.getId());
                    if(null != classStudentList && classStudentList.size() > 0){
                        List<PeStuMessage> stuMessageList = Lists.newArrayList();
                        for(PrClassStudent classStudent : classStudentList){
                            PeStuMessage stuMessage = new PeStuMessage();
                            stuMessage.setCreateTime(new Date());
                            stuMessage.setBusinessId(businessId);
                            stuMessage.setType(type);
                            stuMessage.setClassId(peClass.getId());
                            stuMessage.setStuId(classStudent.getStuId());
                            stuMessageList.add(stuMessage);
                        }
                        count += stuMessageMapper.batchInsert(stuMessageList);
                    }
                }
            }
        }
        if(type == StuMessageTypeEnum.COMMENT_LEARN.getCode()){ //点评学习总结
            PeLearningComment learningComment = learningCommentMapper.getById(businessId);
            if(null != learningComment){
                PeLearning learning = learningMapper.getById(learningComment.getLearningId());
                if(null != learning){
                    PeStuMessage stuMessage = new PeStuMessage();
                    stuMessage.setCreateTime(new Date());
                    stuMessage.setBusinessId(businessId);
                    stuMessage.setType(type);
                    stuMessage.setClassId(learning.getClassId());
                    stuMessage.setStuId(learning.getStuId());
                    count += stuMessageMapper.insert(stuMessage);
                }
            }
        }
        if(type == StuMessageTypeEnum.PRAISE_LEARN.getCode()){ //表扬学习总结
            PeLearning learning = learningMapper.getById(businessId);
            if(null != learning){
                PeClass peClass = classMapper.getById(learning.getClassId());
                if(null != peClass){
                    PeStuMessage stuMessage = new PeStuMessage();
                    stuMessage.setCreateTime(new Date());
                    stuMessage.setBusinessId(businessId);
                    stuMessage.setType(type);
                    stuMessage.setStuId(learning.getStuId());
                    stuMessage.setClassId(peClass.getId());
                    count += stuMessageMapper.insert(stuMessage);
                }
            }
        }
        return count;
    }


    private StuMessageDTO wrapMessageDTO(PeStuMessage message) {
        //业务表ID
        int businessId = message.getBusinessId();
        StuMessageDTO stuMessageDTO = new StuMessageDTO();
        //stuMessageDTO.setCreateTime(message.getCreateTime());
        stuMessageDTO.setType(message.getType());
        PeClass peClass = classMapper.getById(message.getClassId());
        if(null == peClass){
            throw new ClubServiceException(SystemErrorCode.INVALID_DATA);
        }
        PeTeacher teacher = teacherMapper.getById(peClass.getTeachId());
        if(null == teacher){
            throw new ClubServiceException(SystemErrorCode.INVALID_DATA);
        }
        stuMessageDTO.setTeacher(teacher);
/*        PeStudent student = studentMapper.getById(message.getStuId());
        if(null == student){
            throw new ClubServiceException(SystemErrorCode.INVALID_DATA);
        }
        stuMessageDTO.setStudent(student);*/
        switch (StuMessageTypeEnum.getByCode(message.getType())) {
            case POST_TASK: { //任务通知
                PeTask task = taskMapper.getById(businessId);
                if (null != task) {
                    TaskDTO taskDTO = wrapTaskDTO(task);
                    stuMessageDTO.setTaskDTO(taskDTO);
                    stuMessageDTO.setCreateTime(task.getCreateTime());
                }
                break;
            }
            case POST_NOTICE: { //班级通知
                PeClassNotice classNotice = classNoticeMapper.getById(businessId);
                if (null != classNotice) {
                    stuMessageDTO.setClassNotice(classNotice);
                    stuMessageDTO.setCreateTime(classNotice.getPublishTime());

                }
                break;
            }
            case COMMENT_LEARN: { //点评通知
                //点评
                PeLearningComment learningComment = learningCommentMapper.getById(businessId);
                if (null != learningComment) {
                    stuMessageDTO.setCreateTime(learningComment.getCreateTime());
                    stuMessageDTO.setLearningComment(learningComment);
                    //学习总结
                    PeLearning learning = learningMapper.getById(learningComment.getLearningId());
                    if (null != learning) {
                        LearningDTO learningDTO = wrapLearningDTO(learning);
                        stuMessageDTO.setLearningDTO(learningDTO);
                        PeStudent student = studentMapper.getById(learning.getStuId());
                        stuMessageDTO.setStudent(student);
                    }else{
                        return null;
                    }
                }
                break;
            }
            case PRAISE_LEARN: { //表扬通知
                //学习总结
                PeLearning learning = learningMapper.getById(businessId);
                if (null != learning) {
                    LearningDTO learningDTO = wrapLearningDTO(learning);
                    stuMessageDTO.setLearningDTO(learningDTO);
                    PeStudent student = studentMapper.getById(learning.getStuId());
                    stuMessageDTO.setStudent(student);
                    PeStuMessage stuMessage = stuMessageMapper.getByStuIdClassIdBusinessId(learning.getStuId(),learning.getClassId(),businessId,StuMessageTypeEnum.PRAISE_LEARN.getCode());
                    if(null != stuMessage){
                        stuMessageDTO.setCreateTime(stuMessage.getCreateTime());
                    }
                }else{
                    return null;
                }
            }
            break;
        }
        return stuMessageDTO;
    }

    private TaskDTO wrapTaskDTO(PeTask task) {
        TaskDTO taskDTO = new TaskDTO();
        BeanUtils.copyProperties(taskDTO,task);
        String resourceIdsJson = task.getResourceIds();
        if(StringUtils.isNoneBlank(resourceIdsJson)){
            List<Integer> resourceIds = JSON.parseArray(resourceIdsJson,Integer.class);
            List<PeResource> resourceList = Lists.newArrayList();
            for(Integer resourceId : resourceIds){
                PeResource resource = resourceMapper.getById(resourceId);
                resourceList.add(resource);
            }
            taskDTO.setResourceList(resourceList);
        }
        return taskDTO;
    }

    private LearningDTO wrapLearningDTO(PeLearning learning) {
        LearningDTO learningDTO = new LearningDTO();
        BeanUtils.copyProperties(learningDTO,learning);
        String resourceIdsJson = learning.getResourceIds();
        if(StringUtils.isNoneBlank(resourceIdsJson)){
            List<Integer> resourceIds = JSON.parseArray(resourceIdsJson,Integer.class);
            List<PeResource> resourceList = Lists.newArrayList();
            for(Integer resourceId : resourceIds){
                PeResource resource = resourceMapper.getById(resourceId);
                resourceList.add(resource);
            }
            learningDTO.setResourceList(resourceList);
        }
        return learningDTO;
    }


}
