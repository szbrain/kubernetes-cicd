package com.koolearn.club.impl;

import com.google.common.collect.Lists;
import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.dto.comment.CommentListDTO;
import com.koolearn.club.entity.PeComment;
import com.koolearn.club.entity.PeLearning;
import com.koolearn.club.entity.PeStudent;
import com.koolearn.club.exception.ClubException;
import com.koolearn.club.mapper.CommentMapper;
import com.koolearn.club.mapper.LearningMapper;
import com.koolearn.club.mapper.StudentMapper;
import com.koolearn.club.service.ICommentService;
import com.koolearn.util.BeanUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lvyangjun on 2018/4/9.
 */
public class CommentServiceImpl implements ICommentService {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private LearningMapper learningMapper;
    @Resource
    private StudentMapper studentMapper;


    @Override
    public PeComment getById(int id) {
        return commentMapper.getById(id);
    }

    @Override
    public Integer checkComment(Integer stuId, Integer learningId, Integer commentId, String content) {
        if(null == stuId){
            throw new ClubException(SystemErrorCode.PARAM_ERROR);
        }
        PeStudent student = studentMapper.getById(stuId);
        if(null == student){
            throw new ClubException(SystemErrorCode.BIZ_STU_NOT_FOUND);
        }
        if(null == learningId){
            throw new ClubException(SystemErrorCode.PARAM_ERROR);
        }
        PeLearning learning = learningMapper.getById(learningId);
        if(null == learning){
            throw new ClubException(SystemErrorCode.BIZ_LEARNING_NOT_FOUND);
        }
        if(null != commentId){
            PeComment comment = commentMapper.getById(commentId);
            if(null == comment){
                throw new ClubException(SystemErrorCode.BIZ_COMMENT_NOT_FOUND);
            }
        }
        PeComment comment = new PeComment();
        comment.setCreateTime(new Date());
        comment.setContent(content);
        comment.setStuId(stuId);
        comment.setLearningId(learningId);
        if(null != commentId){
            comment.setPid(commentId);
        }
        commentMapper.insert(comment);
        return comment.getId();
    }

    @Override
    public List<CommentListDTO> listComment(int learningId) {
        List<CommentListDTO> commentListDTOList = Lists.newArrayList();
        List<PeComment> commentList = commentMapper.listByLearningId(learningId);
        for(PeComment comment : commentList){
            CommentListDTO commentListDTO = new CommentListDTO();
            BeanUtils.copyProperties(commentListDTO, comment);
            PeStudent student = studentMapper.getById(comment.getStuId());
            if(null != student){
                commentListDTO.setStuName(student.getNickname());
            }
            Integer replierId = comment.getReplierId();
            if(null != replierId){
                PeStudent replier = studentMapper.getById(comment.getReplierId());
                if(null != replier){
                    commentListDTO.setReplierName(replier.getNickname());
                }
            }
            commentListDTOList.add(commentListDTO);
        }
        return commentListDTOList;
    }
}
