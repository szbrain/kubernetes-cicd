package com.koolearn.club.mapper;

import com.koolearn.club.entity.PrClassStudent;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface ClassStudentMapper {

    /**
     * 通过班级ID和学生ID查询
     * @param classId
     * @param stuId
     * @return
     */
    PrClassStudent getByClassIdStuId(@Param("classId") int classId, @Param("stuId") int stuId);


    /**
     * 新增记录
     * @param classStudent
     */
    void insert(PrClassStudent classStudent);

    /**
     * 删除记录
     * @param id
     */
    void delete(int id);

    /**
     * 统计当天退出学生数量
     * @param classId
     * @return
     */
    int countQuitStuForToday(int classId);


    /**
     * 通过班级ID查询学生列表
     * @param classId
     * @return
     */
    List<PrClassStudent> listByClassId(int classId);


    /**
     * 更新班级学生关系表
     * @param classStudent
     * @return
     */
    int update(PrClassStudent classStudent);

    /**
     * 更新加入班级的二维码
     * @param url
     * @param id
     * @return
     */
    int updateQRCodeUrl(@Param("url") String url, @Param("id") int id);
}
