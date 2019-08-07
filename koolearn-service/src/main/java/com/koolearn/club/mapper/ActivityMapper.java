package com.koolearn.club.mapper;


import com.koolearn.club.dto.activity.ActivityDetailReqDTO;
import com.koolearn.club.dto.activity.ActivityEditReqDTO;
import com.koolearn.club.dto.activity.ActivityListReqDTO;
import com.koolearn.club.dto.award.CheckAwardReqDTO;
import com.koolearn.club.entity.PeActivity;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@DAO
public interface ActivityMapper {


    /**
     * 创建
     * @param activity
     * @return
     */
    Integer insert(PeActivity activity);

    /**
     * 活动列表
     * @param activityListReqDTO
     * @return
     */
    List<PeActivity> activityList(ActivityListReqDTO activityListReqDTO);

    /**
     * 活动总数
     * @param activityListReqDTO
     * @return
     */
    Integer listCount(ActivityListReqDTO activityListReqDTO);

    /**
     * 活动详情
     * @param activityDetailReqDTO
     * @return
     */
    PeActivity activityDetail(ActivityDetailReqDTO activityDetailReqDTO);

    /**
     * 更新活动
     * @param activityEditReqDTO
     * @return
     */
    int editActivity(ActivityEditReqDTO activityEditReqDTO);

    /**
     * 根据classId和triggerRule查找活动
     * @param checkAwardReqDTO
     * @return
     */
    PeActivity getActivityByClassIdAndTriggerRule(CheckAwardReqDTO checkAwardReqDTO);


    /**
     * 根据ID 查询活动
     * @param id
     * @return
     */
    PeActivity getById(Integer id);

    /**
     *  更新活动状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(@Param("id") Integer id, @Param("status") short status);

    /**
     * 根据班级ID 查询活动
     * @param classId
     * @return
     */
    List<PeActivity> listByClassId(Integer classId);
}