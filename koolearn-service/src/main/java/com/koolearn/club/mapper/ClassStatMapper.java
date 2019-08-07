package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeClassStat;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface ClassStatMapper {
    PeClassStat getById(int id);

    /**
     * 根据班级ID查询班级统计最近10条记录
     * @param classId
     * @return
     */
    List<PeClassStat> listByClassId(int classId);

    /**
     * 根据老师D查询班级统计记录
     * @param teachId
     * @return
     */
    List<PeClassStat> listByTeachId(Integer teachId);

    /**
     *  保存班级统计
     * @param classStat
     * @return
     */
    int insert(PeClassStat classStat);

    /**
     *  根据班级ID 查询当天的班级统计报表
     * @param classId
     * @param yesterday
     * @return
     */
    PeClassStat getByClassId(@Param("classId") int classId, @Param("yesterday") Date yesterday);

    /**
     * 查询指定日期下班级报表
     * @param yesterday
     * @return
     */
    List<PeClassStat> allByDateWechatNocie(@Param("yesterday") Date yesterday);

    /**
     *  更新班级统计表的微信通知状态为已发送
     * @param id
     * @return
     */
    int updateWechatNotice(int id);
}
