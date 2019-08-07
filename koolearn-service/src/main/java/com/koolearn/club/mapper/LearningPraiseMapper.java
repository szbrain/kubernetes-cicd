package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeLearningPraise;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface LearningPraiseMapper {
    PeLearningPraise getById(int id);

    /**
     * 根据学生ID，学习ID查询
     * @param stuId
     * @param learningId
     * @return
     */
    PeLearningPraise getByStuIdLearningId(@Param("stuId") int stuId, @Param("learningId") int learningId);

    /**
     * 新增学习赞扬
     * @param learningPraise
     * @return
     */
    int insert(PeLearningPraise learningPraise);

    /**
     * 根据学习ID查询
     * @param learningId
     * @return
     */
    List<PeLearningPraise> listByLearningId(int learningId);

    /**
     *  根据learningId 删除
     * @param learningId
     * @return
     */
    int deleteByLearningId(int learningId);
}
