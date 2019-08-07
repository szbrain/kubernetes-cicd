package com.koolearn.club.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.koolearn.club.constants.*;
import com.koolearn.club.constants.commons.LearningCommenTypeEnum;
import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.dto.learning.LearningCommentDTO;
import com.koolearn.club.dto.learning.LearningDTO;
import com.koolearn.club.dto.learning.LearningPraiseDTO;
import com.koolearn.club.dto.learning.LearningResourceDTO;
import com.koolearn.club.entity.*;
import com.koolearn.club.exception.ClubServiceException;
import com.koolearn.club.mapper.*;
import com.koolearn.club.service.*;
import com.koolearn.util.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class LearningServiceImpl implements ILearningService {

    @Resource
    private LearningMapper learningMapper;

    @Resource
    private ResourceMapper resourceMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private ClassMapper classMapper;

    @Resource
    private LearningCommentMapper learningCommentMapper;

    @Resource
    private ILearningCommentService learningCommentService;

    @Resource
    private IResourceService resourceService;

    @Resource
    private ITeachMessageService teachMessageService;

    @Resource
    private IStuMessageService stuMessageService;

    @Resource
    private ClassStudentMapper classStudentMapper;

    @Resource
    private LearningPraiseMapper learningPraiseMapper;

    @Resource
    private ILearningPraiseService learningPraiseService;

    @Override
    public PeLearning getById(int id) {
        return learningMapper.getById(id);
    }

    @Override
    public LearningDTO learning(int stuId, int classId) {

        PeLearning learning = learningMapper.learning(stuId, classId);
        LearningDTO learningDTO =null;
        if (learning!=null){
            learningDTO = new LearningDTO();
            BeanUtils.copyProperties(learningDTO, learning);
            PeStudent student = studentMapper.getById(stuId);
            learningDTO.setStudent(student);
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

        }
        return learningDTO;
    }

    @Override
    public PageDTO<LearningDTO> otherLearningHistory(int offset, int pageSize, int stuId, int classId, Date dateTime) {
        PageDTO pageDTO = new PageDTO();
        List<LearningDTO> learningDTOList = Lists.newArrayList();
        List<PeLearning> learningList = learningMapper.otherLearningHistoryOfV1(offset, pageSize, stuId,classId,dateTime);
        for(PeLearning learning : learningList){
            LearningDTO learningDTO = new LearningDTO();
            BeanUtils.copyProperties(learningDTO, learning);
            //学生
            int stuIds = learningDTO.getStuId();
            PeStudent student = studentMapper.getById(stuIds);
            learningDTO.setStudent(student);
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
                learningDTOList.add(learningDTO);
            }
            //学生点赞
            List<LearningPraiseDTO> learningPraiseDTOList = learningPraiseService.listDTOByLearningId(learning.getId());
            if(null != learningPraiseDTOList && learningPraiseDTOList.size() > 0){
                learningDTO.setLearningPraiseDTOList(learningPraiseDTOList);
            }

        }
        pageDTO.setList(learningDTOList);
        pageDTO.setCount(learningMapper.countOtherLearningHistoryOfV1(stuId,classId,dateTime));
        return pageDTO;
    }

    @Override
    public PageDTO<LearningDTO> otherLearningHistory(int offset, int pageSize, int stuId, int classId, Date dateTime, Long timeline) {
        PageDTO pageDTO = new PageDTO();
        List<LearningDTO> learningDTOList = Lists.newArrayList();
        Date dateLine=null;
        if (offset==0&&(timeline==null||timeline==0)){
            dateLine=new Date();
            timeline=dateLine.getTime();
        }else {
            dateLine=new DateTime(timeline).toDate();
        }
        List<PeLearning> learningList = learningMapper.otherLearningHistory(offset, pageSize, stuId,classId,dateTime,dateLine);
        for(PeLearning learning : learningList){
            LearningDTO learningDTO = new LearningDTO();
            BeanUtils.copyProperties(learningDTO, learning);
            //学生
            int stuIds = learningDTO.getStuId();
            PeStudent student = studentMapper.getById(stuIds);
            learningDTO.setStudent(student);
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
                learningDTOList.add(learningDTO);
            }
            //学生点赞
            List<LearningPraiseDTO> learningPraiseDTOList = learningPraiseService.listDTOByLearningId(learning.getId());
            if(null != learningPraiseDTOList && learningPraiseDTOList.size() > 0){
                learningDTO.setLearningPraiseDTOList(learningPraiseDTOList);
            }
            PeLearningPraise learningPraise = learningPraiseMapper.getByStuIdLearningId(stuId,learning.getId());
            if(null != learningPraise){
                learningDTO.setHasLike(true);
            }
        }
        pageDTO.setList(learningDTOList);
        pageDTO.setTimeline(timeline);
        pageDTO.setCount(learningMapper.countOtherLearningHistory(stuId,classId,dateTime,dateLine));
        return pageDTO;
    }

    @Override
    public List<LearningDTO> listHistoryForStu(int stuId, int classId, Date dateTime) {
        List<LearningDTO> learningDTOList = Lists.newArrayList();
        List<PeLearning> learningList = learningMapper.listHistoryForStu(stuId,classId,dateTime);
        for(PeLearning learning : learningList){
            LearningDTO learningDTO = new LearningDTO();
            BeanUtils.copyProperties(learningDTO, learning);
            //学生
            int stuIds = learningDTO.getStuId();
            PeStudent student = studentMapper.getById(stuIds);
            learningDTO.setStudent(student);
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
                learningDTOList.add(learningDTO);
            }
            //学生点赞
            List<LearningPraiseDTO> learningPraiseDTOList = learningPraiseService.listDTOByLearningId(learning.getId());
            if(null != learningPraiseDTOList && learningPraiseDTOList.size() > 0){
                learningDTO.setLearningPraiseDTOList(learningPraiseDTOList);
            }
            PeLearningPraise learningPraise = learningPraiseMapper.getByStuIdLearningId(stuId,learning.getId());
            if(null != learningPraise){
                learningDTO.setHasLike(true);
            }
        }
        return learningDTOList;
    }


    @Override
    public PageDTO<LearningDTO> listHistoryForStu(int offset, int pageSize, Integer stuId, String sid, int classId) {
        Integer ownId = studentMapper.getBySid(sid).getId();
        stuId = (stuId == 0) ?  ownId : stuId;
        PageDTO pageDTO = new PageDTO();
        List<LearningDTO> learningDTOList = Lists.newArrayList();
        List<PeLearning> learningList = learningMapper.listHistoryForStuOfV2(offset,pageSize,stuId,classId);
        for(PeLearning learning : learningList){
            LearningDTO learningDTO = new LearningDTO();
            BeanUtils.copyProperties(learningDTO, learning);
            //学生
            int stuIds = learningDTO.getStuId();
            PeStudent student = studentMapper.getById(stuIds);
            learningDTO.setStudent(student);
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
                learningDTOList.add(learningDTO);
            }
            //学生点赞
            List<LearningPraiseDTO> learningPraiseDTOList = learningPraiseService.listDTOByLearningId(learning.getId());
            if(null != learningPraiseDTOList && learningPraiseDTOList.size() > 0){
                for(LearningPraiseDTO learningPraiseDTO : learningPraiseDTOList){
                    if(learningPraiseDTO.getStuId() == ownId){
                        learningDTO.setHasLike(true);
                    }
                }
                learningDTO.setLearningPraiseDTOList(learningPraiseDTOList);
            }
        }
        pageDTO.setList(learningDTOList);
        pageDTO.setCount(learningMapper.countHistoryForStuOfV2(stuId,classId));
        return pageDTO;
    }

    @Override
    public PageDTO<LearningDTO> listHistoryForStu(int offset, int pageSize, int stuId, int classId) {
        PageDTO pageDTO = new PageDTO();
        List<LearningDTO> learningDTOList = Lists.newArrayList();
        List<PeLearning> learningList = learningMapper.listHistoryForStuOfV2(offset,pageSize,stuId,classId);
        for(PeLearning learning : learningList){
            LearningDTO learningDTO = new LearningDTO();
            BeanUtils.copyProperties(learningDTO, learning);
            //学生
            int stuIds = learningDTO.getStuId();
            PeStudent student = studentMapper.getById(stuIds);
            learningDTO.setStudent(student);
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
                learningDTOList.add(learningDTO);
            }
            //学生点赞
            List<LearningPraiseDTO> learningPraiseDTOList = learningPraiseService.listDTOByLearningId(learning.getId());
            if(null != learningPraiseDTOList && learningPraiseDTOList.size() > 0){
                learningDTO.setLearningPraiseDTOList(learningPraiseDTOList);
            }
            PeLearningPraise learningPraise = learningPraiseMapper.getByStuIdLearningId(stuId,learning.getId());
            if(null != learningPraise){
                learningDTO.setHasLike(true);
            }
        }
        pageDTO.setList(learningDTOList);
        pageDTO.setCount(learningMapper.countHistoryForStuOfV2(stuId,classId));
        return pageDTO;
    }

    @Override
    public PageDTO<LearningDTO> listHistoryForTech(int offset, int pageSize, int classId, Date dateTime, Long timeline) {
        PageDTO pageDTO = new PageDTO();
        List<LearningDTO> learningDTOList = Lists.newArrayList();
        Date dateLine=null;
        if (offset==0&&(timeline==null||timeline==0)){
            dateLine=new Date();
            timeline=dateLine.getTime();
        }else {
            dateLine=new DateTime(timeline).toDate();
        }
        List<PeLearning> learningList = learningMapper.listHistoryForTech(offset, pageSize, classId,dateTime,dateLine);
        for(PeLearning learning : learningList){
            LearningDTO learningDTO = new LearningDTO();
            BeanUtils.copyProperties(learningDTO, learning);
            //学生
            int stuId = learningDTO.getStuId();
            PeStudent student = studentMapper.getById(stuId);
            learningDTO.setStudent(student);
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
                learningDTOList.add(learningDTO);
            }
            //学生点赞
            List<LearningPraiseDTO> learningPraiseDTOList = learningPraiseService.listDTOByLearningId(learning.getId());
            if(null != learningPraiseDTOList && learningPraiseDTOList.size() > 0){
                learningDTO.setLearningPraiseDTOList(learningPraiseDTOList);
            }
            PeLearningPraise learningPraise = learningPraiseMapper.getByStuIdLearningId(stuId,learning.getId());
            if(null != learningPraise){
                learningDTO.setHasLike(true);
            }

        }
        pageDTO.setList(learningDTOList);
        pageDTO.setTimeline(timeline);
        pageDTO.setCount(learningMapper.countHistoryForTech(classId,dateTime,dateLine));
        return pageDTO;
    }

    @Override
    public PageDTO<LearningDTO> listHistoryForTech(int offset, int pageSize, int classId, Date dateTime) {
        PageDTO pageDTO = new PageDTO();
        List<LearningDTO> learningDTOList = Lists.newArrayList();
        List<PeLearning> learningList = learningMapper.listHistoryForTechOfV1(offset, pageSize, classId,dateTime);
        for(PeLearning learning : learningList){
            LearningDTO learningDTO = new LearningDTO();
            BeanUtils.copyProperties(learningDTO, learning);
            //学生
            int stuId = learningDTO.getStuId();
            PeStudent student = studentMapper.getById(stuId);
            learningDTO.setStudent(student);
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
                learningDTOList.add(learningDTO);
            }
            //学生点赞
            List<LearningPraiseDTO> learningPraiseDTOList = learningPraiseService.listDTOByLearningId(learning.getId());
            if(null != learningPraiseDTOList && learningPraiseDTOList.size() > 0){
                learningDTO.setLearningPraiseDTOList(learningPraiseDTOList);
            }
            PeLearningPraise learningPraise = learningPraiseMapper.getByStuIdLearningId(stuId,learning.getId());
            if(null != learningPraise){
                learningDTO.setHasLike(true);
            }
        }
        pageDTO.setList(learningDTOList);
        pageDTO.setCount(learningMapper.countHistoryForTechOfV1(classId,dateTime));
        return pageDTO;
    }

    @Override
    @Transactional
    public Object startLearning(int stuId, int classId) {
        PeStudent student = studentMapper.getById(stuId);
        if(null == student){
            throw new ClubServiceException(SystemErrorCode.BIZ_STU_NOT_FOUND);
        }
        PeClass peClass = classMapper.getById(classId);
        //课程不存在
        if(null == peClass){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }
        //课程已经结束
        if(ClassStatusEnum.FINISH.getCode()==peClass.getStatus()){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_FINISHED);
        }
        //课程未开始
        if(ClassStatusEnum.WAIT.getCode()==peClass.getStatus()){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_WAIT);
        }

        //判定学生是否加入过
        PrClassStudent classStudent = classStudentMapper.getByClassIdStuId(classId,stuId);
        if(null == classStudent){
            throw new ClubServiceException(SystemErrorCode.BIZ_STU_CLASS_UNJOINED);
        }else {
            if (classStudent.getIsQuit()!=null&&classStudent.getIsQuit()==1)
                throw new ClubServiceException(SystemErrorCode.BIZ_STU_CLASS_REMOVED);
        }

        PeLearning learning= learningMapper.getByStuIdClassId(stuId, classId);
        //有未完成的学习动态
        if (learning!=null){
            throw new ClubServiceException(SystemErrorCode.BIZ_HAVE_NOT_FINISHED_LEARNING);
        }else {
            learning = new PeLearning();
            learning.setClassId(classId);
            learning.setStuId(stuId);
            learning.setStartTime(new Date());
            learning.setStatus(SignStatusEnum.START.getCode());
            learningMapper.insert(learning);
        }
        return learning.getId();
    }

    @Override
    @Transactional
    public int finishLearning(LearningResourceDTO learningResourceDTO) {
        PeStudent student = studentMapper.getById(learningResourceDTO.getStuId());
        if(null == student){
            throw new ClubServiceException(SystemErrorCode.BIZ_STU_NOT_FOUND);
        }
        PeClass peClass = classMapper.getById(learningResourceDTO.getClassId());
        if(null == peClass){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }
        //课程已经结束
        if(ClassStatusEnum.FINISH.getCode()==peClass.getStatus()){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_FINISHED);
        }

        PeLearning learning = learningMapper.getByStuIdClassId(learningResourceDTO.getStuId(), learningResourceDTO.getClassId());
        if(null == learning){
            throw new ClubServiceException(SystemErrorCode.BIZ_NOT_START_LEARNING);
        }
        //判定学生是否加入过
        PrClassStudent classStudent = classStudentMapper.getByClassIdStuId(learningResourceDTO.getClassId(),learningResourceDTO.getStuId());
        if(null == classStudent){
            throw new ClubServiceException(SystemErrorCode.BIZ_STU_CLASS_UNJOINED);
        }else {
            if (classStudent.getIsQuit()!=null&&classStudent.getIsQuit()==1)
            throw new ClubServiceException(SystemErrorCode.BIZ_STU_CLASS_REMOVED);
        }
        int count = 0;
        learning.setType(learningResourceDTO.getType());
        learning.setStatus(SignStatusEnum.END.getCode());
        learning.setEndTime(new Date());
        learning.setContent(learningResourceDTO.getContent());
        List<Integer> resourceIds = Lists.newArrayList();
        if (learningResourceDTO.getResourceList()!=null&&learningResourceDTO.getResourceList().size()>0){
            for(PeResource peResource : learningResourceDTO.getResourceList()){
                count += resourceMapper.insert(peResource);
                resourceIds.add(peResource.getId());
            }
        }
        learning.setResourceIds(JSON.toJSONString(resourceIds));
        //更新学习总结表
        count += learningMapper.update(learning);
        final int learningId = learning.getId();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //发消息给老师
                teachMessageService.sendMessage(TeachMessageTypeEnum.LEARN.getCode(), learningId);
            }
        });
        executorService.shutdown();
        return count;
    }

    @Override
    @Transactional
    public int praiseLearning(final int id, short praiseType) {
        PeLearning learning = learningMapper.getById(id);
        if(null == learning){
            throw new ClubServiceException(SystemErrorCode.BIZ_LEARNING_NOT_FOUND);
        }
        if(learning.getIsPraise() == 1){
            throw new ClubServiceException(SystemErrorCode.BIZ_LEARNING_PRAISED);
        }
        int count = learningMapper.updateIsPraise(id, praiseType);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //发消息给学生
                stuMessageService.sendMessage(StuMessageTypeEnum.PRAISE_LEARN.getCode(), id);
            }
        });
        executorService.shutdown();
        return count;
    }

    @Override
    @Transactional
    public int commentLearning(int id, short type, String content,int duration) {
        PeLearning learning = learningMapper.getById(id);
        if(null == learning){
            throw new ClubServiceException(SystemErrorCode.BIZ_LEARNING_NOT_FOUND);
        }
        int classId = learning.getClassId();
        PeClass peClass = classMapper.getById(classId);
        if(null == peClass){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }
        int teachId = peClass.getTeachId();
        //新增学习点评记录
        PeLearningComment learningComment = new PeLearningComment();
        learningComment.setContent(content);
        learningComment.setLearningId(id);
        learningComment.setTeachId(teachId);
        learningComment.setIsReply((short)0);
        learningComment.setType(type);
        learningComment.setCreateTime(new Date());
        if (LearningCommenTypeEnum.VOICE.getCode()==learningComment.getType()){
            learningComment.setDuration(duration);
        }
        learningCommentMapper.insert(learningComment);
        //更新学习总结表是否点评
        learningMapper.updateIsComment(id);

        final int learningCommentId = learningComment.getId();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //发消息给学生
                stuMessageService.sendMessage(StuMessageTypeEnum.COMMENT_LEARN.getCode(), learningCommentId);
            }
        });
        executorService.shutdown();
        return learningCommentId;
    }

    @Override
    @Transactional
    public int deleteLearning(int id) {
        int count = 0;
        PeLearning learning = learningMapper.getById(id);
        if(null == learning){
            throw new ClubServiceException(SystemErrorCode.BIZ_LEARNING_NOT_FOUND);
        }
        count += learningMapper.deleteById(id);
        if(learning.getIsComment() == 1){ //已经点评
            count += learningCommentMapper.deleteByLearningId(id);
        }
        //删除学生点赞
        count += learningPraiseMapper.deleteByLearningId(id);
        return count;
    }

    @Override
    @Transactional
    public int deleteLearning(int id, int stuId) {
        int count = 0;
        PeLearning learning = learningMapper.getById(id);
        if(null == learning){
            throw new ClubServiceException(SystemErrorCode.BIZ_LEARNING_NOT_FOUND);
        }
        if(learning.getStuId() != stuId){
            throw new ClubServiceException(SystemErrorCode.BIZ_LEARNING_NOT_BELONG_STU);
        }
        count += learningMapper.deleteById(id);
        if(learning.getIsComment() == 1){ //已经点评
            count += learningCommentMapper.deleteByLearningId(id);
        }
        //删除学生点赞
        count += learningPraiseMapper.deleteByLearningId(id);
        return count;
    }

    @Override
    @Transactional
    public int praiseLearningForStu(int stuId, int learningId) {
        PeLearningPraise learningPraise = learningPraiseMapper.getByStuIdLearningId(stuId, learningId);
        if(learningPraise != null){
            throw new ClubServiceException(SystemErrorCode.BIZ_LEARNING_PRAISED);
        }
        learningPraise = new PeLearningPraise();
        learningPraise.setCreateTime(new Date());
        learningPraise.setLearningId(learningId);
        learningPraise.setStuId(stuId);
        learningPraise.setType(LearningPraiseEnum.LIKE.getCode());
        return learningPraiseMapper.insert(learningPraise);
    }

    @Override
    public int countLearning(int classId, Date statDate) {
        PeClass peClass = classMapper.getById(classId);
        if(null == peClass){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }
        return learningMapper.countLearning(classId,statDate);
    }

    @Override
    @Transactional
    public int updateIsReminded(int id) {
        return learningMapper.updateIsReminded(id);
    }

    @Override
    @Transactional
    public int checkin(LearningResourceDTO learningResourceDTO) {
        PeStudent student = studentMapper.getById(learningResourceDTO.getStuId());
        if(null == student){
            throw new ClubServiceException(SystemErrorCode.BIZ_STU_NOT_FOUND);
        }
        PeClass peClass = classMapper.getById(learningResourceDTO.getClassId());
        if(null == peClass){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }
        //课程已经结束
        if(ClassStatusEnum.FINISH.getCode()==peClass.getStatus()){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_FINISHED);
        }
        //判定学生是否加入过
        PrClassStudent classStudent = classStudentMapper.getByClassIdStuId(learningResourceDTO.getClassId(),learningResourceDTO.getStuId());
        if(null == classStudent){
            throw new ClubServiceException(SystemErrorCode.BIZ_STU_CLASS_UNJOINED);
        }else {
            if (classStudent.getIsQuit()!=null&&classStudent.getIsQuit()==1)
                throw new ClubServiceException(SystemErrorCode.BIZ_STU_CLASS_REMOVED);
        }
        int count = 0;
        PeLearning learning = new PeLearning();
        learning.setClassId(learningResourceDTO.getClassId());
        learning.setStuId(learningResourceDTO.getStuId());
        learning.setType(learningResourceDTO.getType());
        learning.setStatus(SignStatusEnum.END.getCode());
        learning.setStartTime(new Date());
        learning.setEndTime(new Date());
        learning.setContent(learningResourceDTO.getContent());
        learning.setResourceIds(resourceService.batchInsert(learningResourceDTO.getResourceList()));
        //新增学习总结表
        count += learningMapper.insert(learning);
        final int learningId = learning.getId();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //发消息给老师
                teachMessageService.sendMessage(TeachMessageTypeEnum.LEARN.getCode(), learningId);
            }
        });
        executorService.shutdown();
        return count;
    }

    @Override
    @Transactional
    public int deleteLearningByStuId(int stuId) {
        int count = 0;
        List<PeLearning> learningList = learningMapper.listByStuId(stuId);
        for(PeLearning learning : learningList){
            count += deleteLearning(learning.getId());
        }
        return count;
    }
}
