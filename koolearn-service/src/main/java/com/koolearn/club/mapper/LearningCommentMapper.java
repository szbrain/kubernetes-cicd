package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeLearningComment;
import com.koolearn.framework.mybatis.annotation.DAO;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface LearningCommentMapper {
    PeLearningComment getById(int id);

    /**
     * 新增学习总结评论
     * @param learningComment
     * @return
     */
    int insert(PeLearningComment learningComment);

    /**
     * 根据学习总结ID，删除点评
     * @param learningId
     * @return
     */
    int deleteByLearningId(int learningId);

    /**
     * 通过学习总结ID查询点评
     * @param learningId
     * @return
     */
    List<PeLearningComment> listByLearningId(int learningId);

    /**
     * 通过学习总结ID查询点评以及评论
     * @param learningId
     * @return
     */
    List<PeLearningComment> listAllByLearningId(int learningId);

}
