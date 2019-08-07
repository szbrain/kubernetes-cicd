package com.koolearn.club.impl;

import com.koolearn.club.constants.ClassCommentStatusEnum;
import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.entity.PeClassComment;
import com.koolearn.club.exception.ClubServiceException;
import com.koolearn.club.mapper.ClassCommentMapper;
import com.koolearn.club.service.IClassCommentService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 课程评论
 * Created by lvyangjun on 2018/04/14.
 */
public class ClassCommentServiceImpl implements IClassCommentService {

    @Resource
    private ClassCommentMapper classCommentMapper;


    @Override
    public PeClassComment get(int stuId, int classId) {
        return classCommentMapper.get(stuId, classId);
    }

    @Override
    @Transactional
    public int commit(int stuId, int classId, int score, String content) {
        if (score<0||score>5) {
            throw new ClubServiceException(SystemErrorCode.BIZ_PARAM_ERROR_SCORE_ERROR);
        }
        PeClassComment peClassComment = classCommentMapper.get(stuId, classId);
        int count = 0;
        if (peClassComment == null) {
            peClassComment = new PeClassComment();
            peClassComment.setClassId(classId);
            peClassComment.setContent(content);
            peClassComment.setScore(score);
            peClassComment.setStuId(stuId);
            peClassComment.setCreateTime(new Date());
            peClassComment.setStatus(ClassCommentStatusEnum.COMMENT.getCode());
            count = classCommentMapper.commit(peClassComment);
            if (count <= 0) {
                throw new ClubServiceException(SystemErrorCode.FAIL);
            }
        } else {
            throw new ClubServiceException(SystemErrorCode.BIZ_STU_CLASS_COMMENTED);
        }
        return count;
    }

    @Override
    public List<PeClassComment> listByClassId(int offset, int pageSize, int classId) {
        return classCommentMapper.listByClassId(offset,pageSize,classId);
    }


    @Override
    public int countByClassId(int classId) {
        return classCommentMapper.countByClassId(classId);
    }
}
