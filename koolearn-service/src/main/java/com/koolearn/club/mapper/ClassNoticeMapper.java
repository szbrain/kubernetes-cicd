package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeClassNotice;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface ClassNoticeMapper {
    PeClassNotice getById(int id);

    /**
     * 新增班级公告
     * @param classNotice
     * @return
     */
    int insert(PeClassNotice classNotice);

    /**
     * 通过班级ID查询班级公告
     * @param classId
     * @return
     */
    List<PeClassNotice> listByClassId(int classId);

    /**
     * 通过班级ID分页查询班级公告
     * @param classId
     * @return
     */
    List<PeClassNotice> listByClassIdForPage(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("classId") int classId);

    int countByClassIdForPage(int classId);

    /**
     * 更新班级公告
     * @param classNotice
     * @return
     */
    int update(PeClassNotice classNotice);
}
