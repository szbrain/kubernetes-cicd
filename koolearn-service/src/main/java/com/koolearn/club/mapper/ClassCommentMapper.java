package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeClassComment;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lvyangjun on 2018/04/14.
 */
@DAO
public interface ClassCommentMapper {
    /**
     *
      * @param stuId
     * @param classId
     * @return
     */
    PeClassComment get(@Param("stuId") int stuId, @Param("classId") int classId);

    /**
     *
     * @param peClassComment
     * @return
     */
    int commit(PeClassComment peClassComment);


    /**
     * 查询学生评价列表
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    List<PeClassComment> listByClassId(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("classId") int classId);

    /**
     *  查询学生评价数量
     * @param classId
     * @return
     */
    int countByClassId(int classId);
}
