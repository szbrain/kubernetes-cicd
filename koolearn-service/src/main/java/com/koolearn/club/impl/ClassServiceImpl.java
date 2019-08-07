package com.koolearn.club.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.koolearn.club.constants.*;
import com.koolearn.club.dto.classroom.*;
import com.koolearn.club.dto.classroom.manage.ClassListParamDTO;
import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.entity.*;
import com.koolearn.club.exception.ClubServiceException;
import com.koolearn.club.mapper.*;
import com.koolearn.club.service.*;
import com.koolearn.util.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class ClassServiceImpl implements IClassService {

    @Resource
    private ClassMapper classMapper;

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private ClassContentMapper classContentMapper;

    @Resource
    private ResourceMapper resourceMapper;

    @Resource
    private ClassStudentMapper classStudentMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private ClassNoticeMapper classNoticeMapper;

    @Resource
    private ClassStatMapper classStatMapper;

    @Resource
    private IChannelService channelService;

    @Resource
    private ITeachMessageService teachMessageService;

    @Resource
    private IStuMessageService stuMessageService;

    @Resource
    private IClassCommentService classCommentService;

    @Resource
    private StuMessageMapper stuMessageMapper;

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private IdentifyingCodeMapper identifyingCodeMapper;

    @Resource
    private IResourceService resourceService;


    @Override
    public PeClass getById(int id) {
        return classMapper.getById(id);
    }

    @Override
    public PageDTO<ClassListDTO> listClassForStu(int offset, int pageSize, int stuId) {
        //初始化返回对象
        PageDTO<ClassListDTO> pageDTO = new PageDTO<>();
        List<ClassListDTO> classListDTOList = Lists.newArrayList();
        //查询学生加入的班级列表
        List<PeClass> classList = classMapper.listClassForStu(offset, pageSize, stuId);
        for(PeClass peClass : classList){
            ClassListDTO classListDTO = new ClassListDTO();
            BeanUtils.copyProperties(classListDTO, peClass);
            //查询班级的老师
            Integer teachId = peClass.getTeachId();
            if(null == teachId){
                throw new ClubServiceException(SystemErrorCode.INVALID_DATA);
            }
            PeTeacher teacher = teacherMapper.getById(teachId);
            classListDTO.setTeachNickname(teacher.getNickname());
            classListDTO.setTeachAvatar(teacher.getAvatar());
            //统计班级学生数
            int stuCount = studentMapper.countByClassId(peClass.getId());
            classListDTO.setMemberCount(stuCount);
            classListDTOList.add(classListDTO);
        }
        pageDTO.setList(classListDTOList);
        pageDTO.setCount(classMapper.countClassForStu(stuId));
        return pageDTO;
    }

    @Override
    public ClassDetailDTO getClassForStu(int id) {
        //初始化返回对象
        ClassDetailDTO classDetailDTO = new ClassDetailDTO();
        //班级信息
        PeClass peClass = classMapper.getById(id);
        if(null == peClass){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }
        BeanUtils.copyProperties(classDetailDTO, peClass);
        //统计班级学生数
        int stuCount = studentMapper.countByClassId(peClass.getId());
        classDetailDTO.setMemberCount(stuCount);
        //班级老师
        Integer teachId = peClass.getTeachId();
        PeTeacher teacher = teacherMapper.getById(teachId);
        if(null == teacher){
            throw new ClubServiceException(SystemErrorCode.INVALID_DATA);
        }
        classDetailDTO.setTeachNickname(teacher.getNickname());
        classDetailDTO.setTeachAvatar(teacher.getAvatar());
        classDetailDTO.setTeachDesc(teacher.getDesc());
        classDetailDTO.setTeachIdentity(teacher.getIdentity());
        //班级内容
        ClassContentDTO classContentDTO = new ClassContentDTO();
        Integer classContentId = peClass.getContentId();
        if(null != classContentId){
            PeClassContent classContent = classContentMapper.getById(classContentId);
            if(null == classContent){
                throw new ClubServiceException(SystemErrorCode.INVALID_DATA);
            }
            classContentDTO.setContent(classContent.getContent());
            String resourceIdsJson = classContent.getResourceIds();
            classContentDTO.setResourceList(resourceService.listByIdsJson(resourceIdsJson));
            classDetailDTO.setClassContentDTO(classContentDTO);
        }
        return classDetailDTO;
    }

    @Override
    public void joinClass(int classId, int stuId) {
        PeClass peClass = classMapper.getById(classId);
        if(null == peClass){//未找到班级
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }else {//找到班级
            //学生未发现
            PeStudent student = studentMapper.getById(stuId);
            if(null == student){
                throw new ClubServiceException(SystemErrorCode.BIZ_STU_NOT_FOUND);
            }
            //判定学生是否加入过
            PrClassStudent classStudent = classStudentMapper.getByClassIdStuId(classId, stuId);
            if(null != classStudent){
                if(classStudent.getIsQuit()==1){//退出
                    if (classStudent.getQuitType()==StuQuitTypeEnum.REMOVE.getCode()){//被移出
                        if( classStudent.getIsRejoin()==0){//允许加入
                            checkClass(peClass,stuId);
                            classStudent.setJoinTime(new Date());
                            classStudent.setIsQuit((short)0);
                            classStudent.setQuitType(null);
                            classStudent.setIsRejoin(null);
                            classStudent.setQuitTime(null);
                            classStudentMapper.update(classStudent);
                            dealData(classId, stuId);

                        }else if(classStudent.getIsRejoin()==1) {//不允许加入
                            throw new ClubServiceException(SystemErrorCode.BIZ_STU_CLASS_NO_ALLOWED_JOIN);
                        }
                    }else {//主动退出
                        checkClass(peClass,stuId);
                        classStudent.setJoinTime(new Date());
                        classStudent.setIsQuit((short)0);
                        classStudent.setQuitType(null);
                        classStudent.setIsRejoin(null);
                        classStudent.setQuitTime(null);
                        classStudentMapper.update(classStudent);
                        dealData(classId, stuId);
                    }
                }
            }else {//没有加入过
                checkClass(peClass,stuId);
                //加入班级
                classStudent = new PrClassStudent();
                classStudent.setClassId(classId);
                classStudent.setStuId(stuId);
                classStudent.setJoinTime(new Date());
                classStudentMapper.insert(classStudent);
                dealData(classId, stuId);

            }
        }
    }

    private void dealData(int classId, int stuId) {
        //发送历史班级公告
        List<PeClassNotice> classNoticeList = classNoticeMapper.listByClassId(classId);
        for(PeClassNotice classNotice : classNoticeList){
            PeStuMessage stuMessage = new PeStuMessage();
            stuMessage.setClassId(classId);
            stuMessage.setStuId(stuId);
            stuMessage.setBusinessId(classNotice.getId());
            stuMessage.setType(StuMessageTypeEnum.POST_NOTICE.getCode());
            stuMessage.setCreateTime(classNotice.getPublishTime());
            PeStuMessage stuMessageDB = stuMessageMapper.getByStuIdClassIdBusinessId(stuId,classId,classNotice.getId(),StuMessageTypeEnum.POST_NOTICE.getCode());
            if(stuMessageDB == null){
                stuMessageMapper.insert(stuMessage);
            }
        }
        //发送历史班级任务
        List<PeTask> taskList = taskMapper.listAllByClassId(classId);
        for(PeTask task : taskList){
            PeStuMessage stuMessage = new PeStuMessage();
            stuMessage.setClassId(classId);
            stuMessage.setStuId(stuId);
            stuMessage.setBusinessId(task.getId());
            stuMessage.setType(StuMessageTypeEnum.POST_TASK.getCode());
            stuMessage.setCreateTime(task.getCreateTime());
            PeStuMessage stuMessageDB = stuMessageMapper.getByStuIdClassIdBusinessId(stuId,classId,task.getId(),StuMessageTypeEnum.POST_TASK.getCode());
            if(stuMessageDB == null){
                stuMessageMapper.insert(stuMessage);
            }
        }
    }


    private void  checkClass(PeClass peClass, int stuId){

        //已经结束
        if (ClassStatusEnum.FINISH.getCode()==peClass.getStatus()){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_FINISHED);
        }else if (ClassStatusEnum.WAIT.getCode()==peClass.getStatus()){//未开始
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_WAIT);
        }else {
            //现在时间小于开始时间
            if (null!=peClass.getOpenTime()&&new Date().getTime() < peClass.getOpenTime().getTime()){
                throw new ClubServiceException(SystemErrorCode.BIZ_STU_CLASS_LESS_STARTTIME);
            }
            //现在时间大于结束时间
            if (null!=peClass.getEnrollEndTime()&&new Date().getTime() > peClass.getEnrollEndTime().getTime()){
                throw new ClubServiceException(SystemErrorCode.BIZ_STU_CLASS_GREATER_ENDINGTIME);
            }
        }
        List<PrClassStudent> prClassStudentList=classStudentMapper.listByClassId(peClass.getId());
        boolean isInClass=false;
        for (PrClassStudent prClassStudent : prClassStudentList){
            if (prClassStudent.getStuId()==stuId&&prClassStudent.getIsQuit()!=1){
                isInClass=true;
            }
        }
        //大于限定人数
        if (prClassStudentList!=null&&prClassStudentList.size()>0){
            if (peClass.getUpperLimit()!=null&&peClass.getUpperLimit()!=0
                    &&prClassStudentList.size()>=peClass.getUpperLimit()&&!isInClass){
                throw new ClubServiceException(SystemErrorCode.BIZ_STU_CLASS_COUNT_LIMIT);
            }
        }
    }

    @Override
    public boolean checkJoinClass(int classId, int stuId) {

        List<PrClassStudent> prClassStudentList=classStudentMapper.listByClassId(classId);
        boolean isInClass=false;
        for (PrClassStudent prClassStudent : prClassStudentList){
            if (prClassStudent.getStuId()==stuId&&prClassStudent.getIsQuit()!=1){
                isInClass=true;
            }
        }
        return isInClass;
    }

    @Override
    @Transactional
    public void exitClass(int classId, int stuId) {
        PeClass peClass = classMapper.getById(classId);
        if(null == peClass){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }
        PeStudent student = studentMapper.getById(stuId);
        if(null == student){
            throw new ClubServiceException(SystemErrorCode.BIZ_STU_NOT_FOUND);
        }
        //判定学生是否加入过
        PrClassStudent classStudent = classStudentMapper.getByClassIdStuId(classId, stuId);
        if(null == classStudent){
            throw new ClubServiceException(SystemErrorCode.BIZ_STU_CLASS_UNJOINED);
        }
        if(null != classStudent.getIsQuit() && 1 == classStudent.getIsQuit()){
            throw new ClubServiceException(SystemErrorCode.BIZ_STU_CLASS_REMOVED);
        }
        classStudent.setIsQuit((short)1);
        classStudent.setQuitType(StuQuitTypeEnum.ACTIVE.getCode());
        classStudent.setIsRejoin((short)1);
        classStudent.setQuitTime(new Date());
        classStudentMapper.update(classStudent);
        //退出班级
        //classStudentMapper.delete(classStudent.getId());
    }

    /*@Override
    public void commentClass(int classId, int content) {
    }*/

    //==============================================================================================================
    @Override
    public PageDTO<ClassListDTO> listClassForTeach(int offset, int pageSize, int teachId) {
        //初始化返回对象
        PageDTO<ClassListDTO> pageDTO = new PageDTO<>();
        List<ClassListDTO> classListDTOList = Lists.newArrayList();
        //查询老师
        PeTeacher teacher = teacherMapper.getById(teachId);
        if(null == teacher){
            throw new ClubServiceException(SystemErrorCode.BIZ_TEACH_NOT_FOUND);
        }
        //查询学生加入的班级列表
        List<PeClass> classList = classMapper.listClassForTeach(offset, pageSize, teachId);
        for(PeClass peClass : classList){
            ClassListDTO classListDTO = new ClassListDTO();
            BeanUtils.copyProperties(classListDTO, peClass);
            classListDTO.setTeachNickname(teacher.getNickname());
            classListDTO.setTeachAvatar(teacher.getAvatar());
            //统计班级学生数
            int stuCount = studentMapper.countByClassId(peClass.getId());
            classListDTO.setMemberCount(stuCount);
            classListDTOList.add(classListDTO);
        }
        pageDTO.setList(classListDTOList);
        pageDTO.setCount(classMapper.countClassForTeach(teachId));
        return pageDTO;
    }

    @Override
    @Transactional
    public int createClass(CreateClassDTO createClassDTO) {
        //插入班级免费内对应资源
        List<Integer> resourceIds = Lists.newArrayList();
        List<PeResource> resourceList = createClassDTO.getResourceList();
        if(null != resourceList){
            for(PeResource resource : resourceList){
                resourceMapper.insert(resource);
                resourceIds.add(resource.getId());
            }
        }
        //插入班级免费内容
        PeClassContent classContent = new PeClassContent();
        classContent.setContent(createClassDTO.getContent());
        classContent.setCreateTime(new Date());
        if(resourceIds.size() > 0){
            classContent.setResourceIds(JSON.toJSONString(resourceIds));
        }
        classContentMapper.insert(classContent);
        //插入班级
        PeClass peClass = new PeClass();
        //班级创建码
        if (StringUtils.isNotEmpty(createClassDTO.getCode())){
            PeIdentifyingCode peIdentifyingCode=identifyingCodeMapper.checkCode(createClassDTO.getCode());
            if (peIdentifyingCode!=null){
                peClass.setIdentifyingCodeId(peIdentifyingCode.getId());
                if(peIdentifyingCode.getType()==0&& peIdentifyingCode.getStatus()==0){
                    peIdentifyingCode.setStatus((short) 1);
                    identifyingCodeMapper.updateStatus(peIdentifyingCode);
                }
            }
        }
        peClass.setCreateTime(new Date());
        peClass.setContentId(classContent.getId());
        peClass.setStatus(ClassStatusEnum.ONGOING.getCode());
        peClass.setOpenTime(new Date());
        BeanUtils.copyProperties(peClass, createClassDTO);
        classMapper.insert(peClass);

        final int classId = peClass.getId();
        //创建班级对应的频道
        channelService.createChannel(ChannelTypeEnum.CLASSROOM.getCode(), peClass.getId());
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //发送消息给老师
                teachMessageService.sendMessage(TeachMessageTypeEnum.CREATE_CLASS.getCode(),classId);
            }
        });
        executorService.shutdown();
        return peClass.getId();
    }

    @Override
    public ClassDetailDTO getClassForTeach(int classId) {
        return getClassForStu(classId);
    }

    @Override
    @Transactional
    public int editClass(EditClassDTO editClassDTO) {
        int count = 0;
        Integer id = editClassDTO.getId();
        if(null == id){
            throw new ClubServiceException(SystemErrorCode.PARAM_ERROR);
        }
        PeClass peClass = classMapper.getById(id);
        if(null == peClass){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }
        //BeanUtils.copyProperties(peClass,editClassDTO);
        //更新班级
        if(null != editClassDTO.getIsCharge()){
          peClass.setIsCharge(editClassDTO.getIsCharge());
        }
        if(null != editClassDTO.getAmount()){
            peClass.setAmount(editClassDTO.getAmount());
        }
        if(null != editClassDTO.getCoverUrl()){
            peClass.setCoverUrl(editClassDTO.getCoverUrl());
        }
        if(null != editClassDTO.getName()){
            peClass.setName(editClassDTO.getName());
        }
        if(null != editClassDTO.getOpenTime()){
            peClass.setOpenTime(editClassDTO.getOpenTime());
        }
        if(null != editClassDTO.getEnrollEndTime()){
            peClass.setEnrollEndTime(editClassDTO.getEnrollEndTime());
        }
        if(null != editClassDTO.getUpperLimit()){
            peClass.setUpperLimit(editClassDTO.getUpperLimit());
        }
        if(null != editClassDTO.getDesc()){
            peClass.setDesc(editClassDTO.getDesc());
        }
        if(null != editClassDTO.getIsShark()){
            peClass.setIsShark(editClassDTO.getIsShark());
        }
        if(null != editClassDTO.getSharkCourseName()){
            peClass.setSharkCourseName(editClassDTO.getSharkCourseName());
        }

        if(null != editClassDTO.getIsFeedback()){
            peClass.setIsFeedback(editClassDTO.getIsFeedback());
        }
        if(null != editClassDTO.getFeedbackId()){
            peClass.setFeedbackId(editClassDTO.getFeedbackId());
        }

        count += classMapper.update(peClass);

        String content = editClassDTO.getContent();
        List<PeResource> resourceList = editClassDTO.getResourceList();
        if(null != content || null != resourceList){
            //需要更新班级内容
            PeClassContent classContent = classContentMapper.getById(peClass.getContentId());
            classContent.setContent(content);
            String resourceIdsJson = classContent.getResourceIds();
            //更新资源
            classContent.setResourceIds(resourceService.batchUpdate(resourceIdsJson, resourceList));
            /*//删除资源表中原来的记录
            if(StringUtils.isNoneBlank(resourceIdsJson)){
                List<Integer> resourceIds = JSON.parseArray(resourceIdsJson, Integer.class);
                if (resourceIds.size()>0){
                    resourceMapper.batchDelete(resourceIds);
                }
            }
            if(resourceList.size() > 0){
                //插入更新后的资源
                List<Integer> resourceIds = Lists.newArrayList();
                for(PeResource resource : resourceList){
                    resource.setCreateTime(new Date());
                    resourceMapper.insert(resource);
                    resourceIds.add(resource.getId());
                }
                classContent.setResourceIds(JSON.toJSONString(resourceIds));
            }else {
                classContent.setResourceIds(JSON.toJSONString(new ArrayList<Integer>()));
            }*/
            count += classContentMapper.update(classContent);
        }
        return count;
    }

    @Override
    public PageDTO<ClassMarkListDTO> listClassMark(int offset, int pageSize, int classId) {
        //初始化返回对象
        PageDTO<ClassMarkListDTO> pageDTO = new PageDTO<>();
        List<ClassMarkListDTO> classMarkListDTOList = Lists.newArrayList();

        List<PeClassComment> classCommentList =  classCommentService.listByClassId(offset, pageSize, classId);
        for(PeClassComment classComment : classCommentList){
            ClassMarkListDTO classMarkListDTO = new ClassMarkListDTO();
            BeanUtils.copyProperties(classMarkListDTO, classComment);
            Integer studentId = classComment.getStuId();
            if(null != studentId){
                PeStudent student = studentMapper.getById(studentId);
                if(null != student){
                    classMarkListDTO.setStudent(student);
                }
            }
            classMarkListDTOList.add(classMarkListDTO);
        }
        pageDTO.setList(classMarkListDTOList);
        pageDTO.setCount(classCommentService.countByClassId(classId));
        return pageDTO;
    }

    @Override
    @Transactional
    public int postNotice(int classId, int teachId, String content, List<PeResource> resourceList) {
        PeTeacher teacher = teacherMapper.getById(teachId);
        //教师不存在
        if(null == teacher){
            throw new ClubServiceException(SystemErrorCode.BIZ_TEACH_NOT_FOUND);
        }
        PeClass peClass = classMapper.getById(classId);
        //班级不存在
        if(null == peClass){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }
        //教师不在该班级
        if(teachId != peClass.getTeachId()){
            throw new ClubServiceException(SystemErrorCode.BIZ_TEACH_NOT_IN_CLASS);
        }
        PeClassNotice classNotice = new PeClassNotice();
        classNotice.setPublishTime(new Date());
        classNotice.setContent(content);
        classNotice.setClassId(classId);
        classNotice.setResourceIds(resourceService.batchInsert(resourceList));
        classNoticeMapper.insert(classNotice);
        final int classNoticeId = classNotice.getId();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //发消息给学生
                stuMessageService.sendMessage(StuMessageTypeEnum.POST_NOTICE.getCode(), classNoticeId);
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //发消息给老师
                teachMessageService.sendMessage(TeachMessageTypeEnum.CLASS_NOTICE.getCode(), classNoticeId);
            }
        });
        executorService.shutdown();
        return classNoticeId;
    }

    @Override
    public ClassStatDTO statClass(int classId) {
        ClassStatDTO classStatDTO = new ClassStatDTO();
        PeClass peClass = classMapper.getById(classId);
        if(null == peClass){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }
        Date openTime = peClass.getOpenTime();
        classStatDTO.setOpenTime(openTime);
        //统计当前退出学生数
        int quitStuCount = classStudentMapper.countQuitStuForToday(classId);
        classStatDTO.setQuitStuCount(quitStuCount);
        //统计班级下学生数
        classStatDTO.setStuCount(studentMapper.countByClassId(classId));
        //统计未完成任务学生
        int undoneTaskStuCount = studentMapper.countUndoneTaskByClassId(classId);
        classStatDTO.setUndoneTaskStuCount(undoneTaskStuCount);
        //统计历史
        List<PeClassStat> classStatList = classStatMapper.listByClassId(classId);
        if(null != classStatList){
            Collections.reverse(classStatList);
            classStatDTO.setClassStatList(classStatList);
        }
        return classStatDTO;
    }

    @Override
    @Transactional
    public int finishClass(int classId, int teachId) {
        PeClass peClass = classMapper.getById(classId);
        if(peClass.getStatus() == ClassStatusEnum.FINISH.getCode()){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_FINISHED);
        }
        if(peClass.getTeachId() != teachId){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_PERMISSION);
        }
        return classMapper.updateStatus(classId, ClassStatusEnum.FINISH.getCode());
    }

    /**
     * 根据状态查找班级
     * @param status
     * @return
     */
    @Override
    public List<PeClass> allClassesByStatus(Short status) {
        return classMapper.allClassesByStatus(status);
    }

    @Override
    public PageDTO<ClassNoticeDTO> listClassNoticeForStu(int offset, int pageSize, int classId) {
        //初始化返回对象
        PageDTO<ClassNoticeDTO> pageDTO = new PageDTO<>();
        List<ClassNoticeDTO> classNoticeDTOList = Lists.newArrayList();
        List<PeClassNotice> classNoticeList =  classNoticeMapper.listByClassIdForPage(offset, pageSize, classId);
        for(PeClassNotice classNotice : classNoticeList){
            ClassNoticeDTO classNoticeDTO = new ClassNoticeDTO();
            String resourceIdsJson = classNotice.getResourceIds();
            classNoticeDTO.setResourceList(resourceService.listByIdsJson(resourceIdsJson));
            BeanUtils.copyProperties(classNoticeDTO, classNotice);
            PeClass peClass = classMapper.getById(classNotice.getClassId());
            if(null != peClass){
                PeTeacher teacher = teacherMapper.getById(peClass.getTeachId());
                if(null != teacher){
                    classNoticeDTO.setTeacher(teacher);
                }
            }
            classNoticeDTOList.add(classNoticeDTO);
        }
        pageDTO.setList(classNoticeDTOList);
        pageDTO.setCount(classNoticeMapper.countByClassIdForPage(classId));
        return pageDTO;
    }

    @Override
    public List<PeClassStat> allClassStatsByDateWechatNocie(Date yesterday) {
        return classStatMapper.allByDateWechatNocie(yesterday);
    }

    @Override
    public int updateClassStatsWechatNotice(int classStatsId) {
        return classStatMapper.updateWechatNotice(classStatsId);
    }

    @Override
    @Transactional
    public int editNotice(int noticeId, int teachId, String content, List<PeResource> resourceList) {
        PeTeacher teacher = teacherMapper.getById(teachId);
        //教师不存在
        if(null == teacher){
            throw new ClubServiceException(SystemErrorCode.BIZ_TEACH_NOT_FOUND);
        }
        PeClassNotice classNotice = classNoticeMapper.getById(noticeId);
        //班级公告不存在
        if(null == classNotice){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_NOTICE_NOT_FOUND);
        }
        PeClass peClass = classMapper.getById(classNotice.getClassId());
        //班级不存在
        if(null == peClass){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }
        //教师不属于该班级
        if(teachId != peClass.getTeachId()){
            throw new ClubServiceException(SystemErrorCode.BIZ_TEACH_NOT_IN_CLASS);
        }
        int count = 0;
        String resourceIdsJson = classNotice.getResourceIds();
        classNotice.setContent(content);
        classNotice.setResourceIds(resourceService.batchUpdate(resourceIdsJson, resourceList));
        count += classNoticeMapper.update(classNotice);
        return count;
    }




    //=======================================================================================================================
    @Override
    public PageDTO<com.koolearn.club.dto.classroom.manage.ClassListDTO> listClassForManage(ClassListParamDTO classListParamDTO) {
        PageDTO<com.koolearn.club.dto.classroom.manage.ClassListDTO> pageDTO = new PageDTO<>();
        List<com.koolearn.club.dto.classroom.manage.ClassListDTO> classListDTOList = Lists.newArrayList();
        List<PeClass> classList = classMapper.listClassForManage(classListParamDTO);
        for(PeClass peClass : classList){
            com.koolearn.club.dto.classroom.manage.ClassListDTO classListDTO = new com.koolearn.club.dto.classroom.manage.ClassListDTO();
            BeanUtils.copyProperties(classListDTO, peClass);
            classListDTO.setClassNum(studentMapper.countByClassId(peClass.getId()));
            PeTeacher teacher = teacherMapper.getById(peClass.getTeachId());
            if(null != teacher){
                classListDTO.setTeacher(teacher.getNickname());
            }
            PeIdentifyingCode identifyingCode = identifyingCodeMapper.getById(peClass.getIdentifyingCodeId());
            if(null != identifyingCode){
                classListDTO.setIdentifyingCode(identifyingCode.getCode());
            }
            classListDTOList.add(classListDTO);
        }
        pageDTO.setList(classListDTOList);
        pageDTO.setCount(classMapper.countClassForManage(classListParamDTO));
        return pageDTO;
    }

    @Override
    public ClassStatRespDTO statClassForWebapp(ClassStatReqDTO classStatReqDTO) {
        ClassStatRespDTO classStatRespDTO = new ClassStatRespDTO();
        int classId = classStatReqDTO.getClassId();
        PeClass peClass = classMapper.getById(classId);
        if(null == peClass){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }
        Date openTime = peClass.getOpenTime();
        classStatRespDTO.setOpenTime(openTime);

        //统计班级下学生数
        classStatRespDTO.setStuCount(studentMapper.countByClassId(classId));
        //统计未完成任务学生
        Date startTime = classStatReqDTO.getStartTime();
        Date endTime = classStatReqDTO.getEndTime();
        int undoneTaskStuCount = studentMapper.countUndoneTaskByClassIdAndTime(classId, startTime, endTime);
        classStatRespDTO.setUndoneTaskStuCount(undoneTaskStuCount);
        return classStatRespDTO;
    }

    @Override
    public PageDTO<ClassStatDoneStuListRespDTO> listClassStatDoneStu(ClassStatDoneStuListReqDTO classStatDoneStuListReqDTO) {
        PageDTO<ClassStatDoneStuListRespDTO> pageDTO = new PageDTO<>();
        if(null == classStatDoneStuListReqDTO.getMinCount()){
            classStatDoneStuListReqDTO.setMinCount(0);
        }
        if(null == classStatDoneStuListReqDTO.getMaxCount()){
            classStatDoneStuListReqDTO.setMaxCount(Integer.MAX_VALUE);
        }
        if(null == classStatDoneStuListReqDTO.getMinDayCount()){
            classStatDoneStuListReqDTO.setMinDayCount(0);
        }
        if(null == classStatDoneStuListReqDTO.getMaxDayCount()){
            classStatDoneStuListReqDTO.setMaxDayCount(Integer.MAX_VALUE);
        }
        List<ClassStatDoneStuListRespDTO> classStatDoneStuListRespDTOList = classMapper.listClassStatDoneStu(classStatDoneStuListReqDTO);
        if(null != classStatDoneStuListRespDTOList && classStatDoneStuListRespDTOList.size() > 0){
            for(ClassStatDoneStuListRespDTO classStatDoneStuListRespDTO : classStatDoneStuListRespDTOList){
                int stuId = classStatDoneStuListRespDTO.getStuId();
                PeStudent student = studentMapper.getById(stuId);
                if(null != student){
                    classStatDoneStuListRespDTO.setStudent(student);
                }
            }
        }
        pageDTO.setList(classStatDoneStuListRespDTOList);
        pageDTO.setCount(classMapper.countClassStatDoneStu(classStatDoneStuListReqDTO));
        return pageDTO;
    }

    @Override
    public List<Integer> listClassStatUndoneStu(ClassStatRemindUndoneStuReqDTO classStatRemindUndoneStuReqDTO) {
        List<Integer> stuIdList = classMapper.listClassStatUndoneStu(classStatRemindUndoneStuReqDTO);
        return stuIdList;
    }
}
