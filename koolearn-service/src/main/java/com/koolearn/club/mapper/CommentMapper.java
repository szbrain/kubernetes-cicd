package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeComment;
import com.koolearn.framework.mybatis.annotation.DAO;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface CommentMapper {
    PeComment getById(int id);

    /**
     * 保存评论
     * @param comment
     * @return
     */
    int insert(PeComment comment);

    /**
     * 根据学习ID，查询列表
     * @param learningId
     * @return
     */
    List<PeComment> listByLearningId(int learningId);
}
