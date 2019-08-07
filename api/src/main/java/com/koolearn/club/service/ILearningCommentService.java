package com.koolearn.club.service;

import com.koolearn.club.dto.learning.LearningCommentDTO;
import com.koolearn.club.entity.PeLearningComment;

import java.util.List;
import java.util.Map;

/**
 * Created by lilong01 on 2018/2/27.
 */
public interface ILearningCommentService {

    PeLearningComment getById(int id);
    List<LearningCommentDTO> ListDTOByLearningId(int learningId);

    /**
     * 学生回复教师点评
     *
     * @param stuId
     * @param learningId
     * @param type
     * @param content
     * @param duration
     * @return
     */
    int replyComment(Integer stuId, int learningId, short type, String content, int duration, short replyType);


    /**
     * learningIds列表，查询教师学生评论列表
     * @param learningIds
     * @return
     */
    List listByLearningIds(List<String> learningIds);
}
