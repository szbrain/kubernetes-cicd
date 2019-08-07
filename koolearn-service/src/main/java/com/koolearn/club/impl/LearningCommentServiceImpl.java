package com.koolearn.club.impl;

import com.google.common.collect.Lists;
import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.constants.commons.LearningCommenTypeEnum;
import com.koolearn.club.dto.learning.LearningCommentDTO;
import com.koolearn.club.entity.PeClass;
import com.koolearn.club.entity.PeLearning;
import com.koolearn.club.entity.PeLearningComment;
import com.koolearn.club.entity.PeTeacher;
import com.koolearn.club.exception.ClubServiceException;
import com.koolearn.club.mapper.ClassMapper;
import com.koolearn.club.mapper.LearningCommentMapper;
import com.koolearn.club.mapper.LearningMapper;
import com.koolearn.club.mapper.TeacherMapper;
import com.koolearn.club.service.ILearningCommentService;
import com.koolearn.util.BeanUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class LearningCommentServiceImpl implements ILearningCommentService {

    @Resource
    private LearningCommentMapper learningCommentMapper;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private LearningMapper learningMapper;
    @Resource
    private ClassMapper classMapper;

    @Override
    public PeLearningComment getById(int id) {
        return learningCommentMapper.getById(id);
    }

    @Override
    public List<LearningCommentDTO> ListDTOByLearningId(int learningId) {
        List<LearningCommentDTO> learningCommentDTOList = Lists.newArrayList();
        List<PeLearningComment> learningCommentList = learningCommentMapper.listByLearningId(learningId);
        for(PeLearningComment learningComment : learningCommentList){
            LearningCommentDTO learningCommentDTO = new LearningCommentDTO();
            BeanUtils.copyProperties(learningCommentDTO, learningComment);
            int teachId = learningComment.getTeachId();
            PeTeacher teacher = teacherMapper.getById(teachId);
            learningCommentDTO.setTeacher(teacher);
            learningCommentDTOList.add(learningCommentDTO);
        }
        return learningCommentDTOList;
    }

    @Override
    public int replyComment(Integer studentId, int learningId, short type, String content, int duration, short replyType) {
        PeLearning learning = learningMapper.getById(learningId);
        if(null == learning){
            throw new ClubServiceException(SystemErrorCode.BIZ_LEARNING_NOT_FOUND);
        }
        if(null != studentId && learning.getStuId() != studentId){
            throw new ClubServiceException(SystemErrorCode.BIZ_LEARNING_COMMENT_REPLY_DENY);
        }
        int classId = learning.getClassId();
        PeClass peClass = classMapper.getById(classId);
        if(null == peClass){
            throw new ClubServiceException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }
        //新增学习点评记录
        PeLearningComment learningComment = new PeLearningComment();
        learningComment.setContent(content);
        learningComment.setLearningId(learning.getId());
        learningComment.setTeachId(peClass.getTeachId());
        learningComment.setType(type);
        learningComment.setIsReply((short)1); //回复
        learningComment.setReplyType(replyType);
        learningComment.setCreateTime(new Date());
        if (LearningCommenTypeEnum.VOICE.getCode()==learningComment.getType()){
            learningComment.setDuration(duration);
        }
        learningCommentMapper.insert(learningComment);
        return learningComment.getId();
    }

    @Override
    public List listByLearningIds(List<String> learningIds) {
        List<List> list = Lists.newArrayList();
        for(String learningId : learningIds){
            List<LearningCommentDTO> learningCommentDTOList = Lists.newArrayList();
            List<PeLearningComment> learningCommentList = learningCommentMapper.listAllByLearningId(Integer.parseInt(learningId));
            for(PeLearningComment learningComment : learningCommentList){
                LearningCommentDTO learningCommentDTO = new LearningCommentDTO();
                BeanUtils.copyProperties(learningCommentDTO, learningComment);
                Integer teachId = learningComment.getTeachId();
                if(null != teachId){
                    learningCommentDTO.setTeacher(teacherMapper.getById(teachId));
                }
                learningCommentDTOList.add(learningCommentDTO);
            }
            list.add(learningCommentDTOList);
        }
        return list;
    }
}
