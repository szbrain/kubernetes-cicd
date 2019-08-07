package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeTask;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface TaskMapper {
    PeTask getById(int id);

    /**
     * 根据班级ID，查询最新的任务
     * @param classId
     * @return
     */
    PeTask getLastTaskByClassId(int classId);


    /**
     * V2 根据班级ID，查询最新的任务
     * @param classId
     * @return
     */
    PeTask getLastTaskByClassIdOfV2(int classId);


    /**
     * 根据班级ID，分页查询任务
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    List<PeTask> listByClassId(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("classId") int classId);

    /**
     * 根据班级ID，分页查询任务
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    List<PeTask> listByClassIdOfV2(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("classId") int classId);

    /**
     * 根据班级ID，查询任务总数
     * @param classId
     * @return
     */
    int countByClassId(int classId);

    /**
     * 根据班级ID，查询任务总数
     * @param classId
     * @return
     */
    int countByClassIdOfV2(int classId);

    /**
     * 新增任务
     * @param task
     * @return
     */
    int insert(PeTask task);

    /**
     * 根据班级ID查询所有任务
     * @param classId
     * @return
     */
    List<PeTask> listAllByClassId(int classId);

    /**
     * 更新任务
     * @param task
     * @return
     */
    int update(PeTask task);
}
