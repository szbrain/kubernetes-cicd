package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeClassShareRecord;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lvyangjun on 2018/04/14.
 */
@DAO
public interface ClassShareRecordMapper {


    /**
     *查询学员分享课程记录
     * @param stuId
     * @param classId
     * @return
     */
    PeClassShareRecord get(@Param("stuId") int stuId, @Param("classId") int classId);



    /**
     *
     * 查询学员分享课程记录列表
     * @param classId
     * @return
     */
    List<PeClassShareRecord> getList(@Param("offset") int offset,
                                     @Param("pageSize") int pageSize,
                                     @Param("classId") int classId);


    /**
     *
     * 查询学员分享课程记录总数
     * @param classId
     * @return
     */
    List<PeClassShareRecord> getListCount(@Param("classId") int classId);



    /**
     *保存学员分享课程记录
     * @param classShareRecord
     * @return
     */
    int save(PeClassShareRecord classShareRecord);
}
