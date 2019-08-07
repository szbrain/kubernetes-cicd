package com.koolearn.club.impl;

import com.google.common.collect.Lists;
import com.koolearn.club.constants.ChannelTypeEnum;
import com.koolearn.club.constants.TeachMessageTypeEnum;
import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.dto.learning.LearningCommentDTO;
import com.koolearn.club.dto.learning.LearningDTO;
import com.koolearn.club.dto.message.TeachMessageDTO;
import com.koolearn.club.entity.*;
import com.koolearn.club.mapper.*;
import com.koolearn.club.service.IChannelService;
import com.koolearn.club.service.ILearningCommentService;
import com.koolearn.club.service.IResourceService;
import com.koolearn.club.service.ITeachMessageService;
import com.koolearn.util.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class TeachMessageServiceImpl implements ITeachMessageService {

    @Resource
    private TeachMessageMapper teachMessageMapper;

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private ClassMapper classMapper;

    @Resource
    private LearningMapper learningMapper;

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private ClassStatMapper classStatMapper;

    @Resource
    private ILearningCommentService learningCommentService;

    @Resource
    private ChannelMapper channelMapper;

    @Resource
    private ClassNoticeMapper classNoticeMapper;

    @Resource
    private IResourceService resourceService;

    @Resource
    private IChannelService channelService;

    @Override
    public PeTeachMessage getById(int id) {
        return teachMessageMapper.getById(id);
    }

    @Override
    public PageDTO<TeachMessageDTO> listByChannelId(int offset, int pageSize, int channelId) {
        //初始化返回对象
        PageDTO<TeachMessageDTO> pageDTO = new PageDTO<>();
        List<TeachMessageDTO> teachMessageDTOList = Lists.newArrayList();
        //查询消息列表
        List<PeTeachMessage> teachMessageList = teachMessageMapper.listByChannelId(offset, pageSize, channelId);
        for (PeTeachMessage teachMessage : teachMessageList) {
            TeachMessageDTO teachMessageDTO = new TeachMessageDTO();
            teachMessageDTO.setType(teachMessage.getType());
            teachMessageDTO.setTypeName(TeachMessageTypeEnum.getNameByCode(teachMessage.getType()));
            teachMessageDTO.setCreateTime(teachMessage.getCreateTime());
            //业务表ID
            Integer businessId = teachMessage.getBusinessId();
            switch (TeachMessageTypeEnum.getByCode(teachMessage.getType())) {
                case CREATE_CLASS: { //班级创建
                    PeClass peClass = classMapper.getById(businessId);
                    if (null != peClass) {
                        teachMessageDTO.setPeClass(peClass);
                        teachMessageDTOList.add(teachMessageDTO);
                    }
                    PeTeacher teacher = teacherMapper.getById(peClass.getTeachId());
                    if(null != teacher){
                        teachMessageDTO.setTeacher(teacher);
                    }
                    break;
                }
                case LEARN: { //学习总结
                    PeLearning learning = learningMapper.getById(businessId);
                    if (null != learning) {
                        LearningDTO learningDTO = new LearningDTO();
                        BeanUtils.copyProperties(learningDTO, learning);
                        //资源
                        String resourceIdsJson = learning.getResourceIds();
                        if(StringUtils.isNoneBlank(resourceIdsJson)){
                            List<PeResource> resourceList = resourceService.listByIdsJson(resourceIdsJson);
                            learningDTO.setResourceList(resourceList);
                            //点评
                            if(learning.getIsComment() == 1){
                                List<LearningCommentDTO> learningCommentDTOList = learningCommentService.ListDTOByLearningId(learning.getId());
                                learningDTO.setLearningCommentList(learningCommentDTOList);
                            }
                        }
                        PeStudent student = studentMapper.getById(learning.getStuId());
                        if(null != student){
                            teachMessageDTO.setStudent(student);
                        }
                        teachMessageDTO.setLearningDTO(learningDTO);
                        teachMessageDTOList.add(teachMessageDTO);
                    }
                    break;
                }
                case POST_TASK: { //发布任务
                    PeTask task = taskMapper.getById(businessId);
                    if (null != task) {
                        teachMessageDTO.setTask(task);
                        teachMessageDTOList.add(teachMessageDTO);
                    }
                    PeClass peClass = classMapper.getById(task.getClassId());
                    if(null != peClass){
                        PeTeacher teacher = teacherMapper.getById(peClass.getTeachId());
                        if(null != teacher){
                            teachMessageDTO.setTeacher(teacher);
                        }
                    }
                    break;
                }
                case STAT_STU: { //学习人数统计
                    PeClassStat classStat = classStatMapper.getById(businessId);
                    if (null != classStat) {
                        teachMessageDTO.setClassStat(classStat);
                        teachMessageDTOList.add(teachMessageDTO);
                    }
                    break;
                }
                case CLASS_NOTICE:{ //班级公告
                    PeClassNotice classNotice = classNoticeMapper.getById(businessId);
                    if(null != classNotice){
                        teachMessageDTO.setClassNotice(classNotice);
                        teachMessageDTOList.add(teachMessageDTO);
                    }
                    PeClass peClass = classMapper.getById(classNotice.getClassId());
                    if(null != peClass){
                        PeTeacher teacher = teacherMapper.getById(peClass.getTeachId());
                        if(null != teacher){
                            teachMessageDTO.setTeacher(teacher);
                        }
                    }
                    break;
                }
                case NEW_JOIN:{ //新增学员
                    PeClassStat classStat = classStatMapper.getById(businessId);
                    if(null != classStat){
                        PeClass peClass = classMapper.getById(classStat.getClassId());
                        if(null != peClass){
                            teachMessageDTO.setPeClass(peClass);
                            teachMessageDTOList.add(teachMessageDTO);
                            //统计新增学员
                            List<PeStudent> studentList = studentMapper.listForNewJoin(classStat.getId());
                            if(null != studentList){
                                teachMessageDTO.setStudentList(studentList);
                            }
                        }
                    }
                    break;
                }
                case STAT_CLASS:{ //班级学习统计
                    PeClassStat classStat= classStatMapper.getById(businessId);
                    if(null != classStat){
                        TeachMessageDTO.ClassStatDTO classStatDTO = teachMessageDTO.new ClassStatDTO();
                        BeanUtils.copyProperties(classStatDTO,classStat);
                        PeClass peClass = classMapper.getById(classStat.getClassId());
                        classStatDTO.setPeClass(peClass);
                        teachMessageDTO.setClassStatDTO(classStatDTO);
                        teachMessageDTOList.add(teachMessageDTO);
                    }
                    break;
                }
            }
        }
        //频道中的未读消息置为0;
        channelMapper.updateUnreadCount(channelId,0);
        pageDTO.setList(teachMessageDTOList);
        pageDTO.setCount(teachMessageMapper.countByChannelId(channelId));
        return pageDTO;
    }

    @Override
    @Transactional
    public int sendMessage(short type, int businessId) {
        final String CREATE_CLASS_TEMPLATE = "{0}班级已创建";
        final String LEARN_TEMPLATE = "{0}:提交了总结";
        final String POST_TASK_TEMPLATE = "{0}任务发布成功";
        final String CLASS_NOTICE_TEMPLATE = "班级公告已发布";
        final String NEW_JOIN_TEMPLATE = "{0}新增{1}人";
        final String STAT_CLASS_TEMPLATE = "{0}报表已统计";

        PeTeachMessage teachMessage = new PeTeachMessage();
        teachMessage.setBusinessId(businessId);
        teachMessage.setCreateTime(new Date());
        teachMessage.setType(type);
        String content = "";
        int channelId = 0;
        //处理班级频道的消息
        if(type == TeachMessageTypeEnum.CREATE_CLASS.getCode()){ //创建班级
            PeClass peClass = classMapper.getById(businessId);
            if(null != peClass){
                content = MessageFormat.format(CREATE_CLASS_TEMPLATE,peClass.getName());
            }
            PeChannel channel = channelMapper.getByBusinessId(businessId);
            channelId = channel.getId();
        }
        if(type == TeachMessageTypeEnum.LEARN.getCode()){ //学习总结
            PeLearning learning = learningMapper.getById(businessId);
            if(null != learning){
                PeClass peClass = classMapper.getById(learning.getClassId());
                if(null != peClass){
                    int stuId = learning.getStuId();
                    PeStudent student = studentMapper.getById(stuId);
                    if(null != student){
                        content = MessageFormat.format(LEARN_TEMPLATE,student.getNickname());
                    }
                    PeChannel channel = channelMapper.getByBusinessId(peClass.getId());
                    channelId = channel.getId();
                }
            }
        }
        if(type == TeachMessageTypeEnum.POST_TASK.getCode()){ //发布任务
            PeTask task = taskMapper.getById(businessId);
            if(null != task){
                content = MessageFormat.format(POST_TASK_TEMPLATE,task.getContent());
                PeClass peClass = classMapper.getById(task.getClassId());
                if(null != peClass){
                    PeChannel channel = channelMapper.getByBusinessId(peClass.getId());
                    channelId = channel.getId();
                }
            }
        }
        if(type == TeachMessageTypeEnum.CLASS_NOTICE.getCode()) { //班级公告
            content = CLASS_NOTICE_TEMPLATE;
            PeClassNotice classNotice = classNoticeMapper.getById(businessId);
            if(null != classNotice){
                PeClass peClass = classMapper.getById(classNotice.getClassId());
                if(null != peClass){
                    PeChannel channel = channelMapper.getByBusinessId(peClass.getId());
                    channelId = channel.getId();
                }
            }
        }
        if(type == TeachMessageTypeEnum.NEW_JOIN.getCode()) { //新增学员
            PeClassStat classStat = classStatMapper.getById(businessId);
            if(null != classStat){
                PeClass peClass = classMapper.getById(classStat.getClassId());
                if(null != peClass){
                    content = MessageFormat.format(NEW_JOIN_TEMPLATE,peClass.getName(),classStat.getJoinCount());
                    PeChannel channel = channelMapper.getByTeachIdType(peClass.getTeachId(),ChannelTypeEnum.ADD_STU.getCode());
                    if(null == channel){
                        //创建新增学员频道
                        channelId = channelService.createChannel(ChannelTypeEnum.ADD_STU.getCode(),peClass.getTeachId());
                    }else{
                        channelId = channel.getId();
                    }
                }
            }
        }
        if(type == TeachMessageTypeEnum.STAT_CLASS.getCode()) { //班级报表统计
            PeClassStat classStat = classStatMapper.getById(businessId);
            if(null != classStat){
                PeClass peClass = classMapper.getById(classStat.getClassId());
                if(null != peClass){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    content = MessageFormat.format(STAT_CLASS_TEMPLATE,format.format(classStat.getStatTime()));
                    PeChannel channel = channelMapper.getByTeachIdType(peClass.getTeachId(),ChannelTypeEnum.LEARN_STAT.getCode());
                    if(null == channel){
                        //创建班级学习报表频道
                        channelId = channelService.createChannel(ChannelTypeEnum.LEARN_STAT.getCode(),peClass.getTeachId());
                    }else{
                        channelId = channel.getId();
                    }
                }
            }
        }
        teachMessage.setChannelId(channelId);
        teachMessageMapper.insert(teachMessage);
        channelMapper.updateContent(content,channelId);//更新频道内容
        return teachMessage.getId();
    }


}
