package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeClassMark;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface ClassMarkMapper {
    PeClassMark getById(int id);

    /**
     * 查询学生评价列表
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    List<PeClassMark> listByClassId(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("classId") int classId);

    /**
     *  查询学生评价数量
     * @param classId
     * @return
     */
    int countByClassId(int classId);
}
