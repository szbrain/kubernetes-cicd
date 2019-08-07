package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeLive;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface LiveMapper {
    PeLive getById(int id);

    /**
     * 创建直播
     * @param live
     * @return
     */
    int insert(PeLive live);

    /**
     * 根据班级ID 分页查询直播
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    List<PeLive> listByClassId(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("classId") int classId);

    int countByClassId(int classId);

    /**
     * 根据任务ID 查询直播
     * @param taskId
     * @return
     */
    PeLive getByTaskId(int taskId);

    /**
     * 更新直播
     * @param live
     * @return
     */
    int update(PeLive live);

    /**
     * 更新直播状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(@Param("id") int id, @Param("status") short status);

    /**
     * 更新直播任务在线人数
     * @param id
     * @param memberNum
     * @param date
     * @return
     */
    int updateOnlineCount(@Param("id") int id, @Param("memberNum") int memberNum, @Param("date") Date date);

    /**
     * 根据任务ID 更新直播通知
     * @param taskId
     * @param notice
     * @return
     */
    int updateNoticeByTaskId(@Param("taskId") int taskId, @Param("notice") String notice);

    /**
     * 更新播放地址
     * @param taskId
     * @param playUrl
     * @return
     */
    int updatePlayUrl(@Param("taskId") int taskId, @Param("playUrl") String playUrl);
}
