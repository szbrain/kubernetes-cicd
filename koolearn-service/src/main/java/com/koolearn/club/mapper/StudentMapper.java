package com.koolearn.club.mapper;

import com.koolearn.club.dto.member.MemberManageListReqDTO;
import com.koolearn.club.dto.member.MemberManageListRespDTO;
import com.koolearn.club.entity.PeStudent;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface StudentMapper {
    PeStudent getById(int id);

    /**
     * 根据班级ID分页查询学生列表
     *
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    List<PeStudent> listByClassId(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("classId") int classId);

    /**
     * 成员管理查询列表
     * @param memberManageListReqDTO
     * @return
     */
    List<MemberManageListRespDTO> memberListByClassId(MemberManageListReqDTO memberManageListReqDTO);

    /**
     * 成员管理查询列表总数
     * @param memberManageListReqDTO
     * @return
     */
    Integer countMemberListByClassId(MemberManageListReqDTO memberManageListReqDTO);

    /**
     * 根据班级ID分页查询学生总数
     *
     * @param classId
     * @return
     */
    int countByClassId(int classId);

    /**
     * 查询当前未完成任务的学生列表
     *
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    List<PeStudent> listUndoneTaskByClassId(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("classId") int classId);

    /**
     * 查询当前未完成任务的学生总数
     *
     * @param classId
     * @return
     */
    int countUndoneTaskByClassId(int classId);

    /**
     * 查询当前退出的学生列表
     *
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    List<PeStudent> listQuitStuByClassId(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("classId") int classId);

    /**
     * 查询当前退出的学生总数
     *
     * @param classId
     * @return
     */
    int countQuitStuByClassId(int classId);

    /**
     * 根据classId 查询所有学生
     *
     * @param classId
     * @return
     */
    List<PeStudent> listAllByClassId(int classId);

    /**
     * 根据openId查询学生
     *
     * @param openId
     * @return
     */
    PeStudent getByOpenId(String openId);

    /**
     * 更新sid
     *
     * @param sid
     * @param id
     * @return
     */
    int updateSid(@Param("sid") String sid, @Param("id") int id);

    /**
     * 新增学生对象
     *
     * @param student
     * @return
     */
    int insert(PeStudent student);

    /**
     * 更新老师对象
     *
     * @param student
     * @return
     */
    int update(PeStudent student);

    /**
     * 根据sid查询学生
     *
     * @param sid
     * @return
     */
    PeStudent getBySid(String sid);

    /**
     * 根据班级统计ID 查询新增学员
     *
     * @param classStatId
     * @return
     */
    List<PeStudent> listForNewJoin(int classStatId);

    /**
     * 根据班级查询新加入的学生
     *
     * @param classId
     * @return
     */
    List<PeStudent> listAllJoinStuByClassId(@Param("classId") int classId, @Param("yesterday") Date yesterday);

    /**
     * 根据班级查询退出的学生
     *
     * @param classId
     * @return
     */
    List<PeStudent> listAllQuitStuByClassId(@Param("classId") int classId, @Param("yesterday") Date yesterday);

    /**
     * 根据班级查询未完成任务的学生
     *
     * @param classId
     * @return
     */
    List<PeStudent> listAllUndoneTaskByClassId(@Param("classId") int classId, @Param("yesterday") Date yesterday);

    /**
     *  根据班级ID和时间区间查询未完成任务的学生
     * @param classId
     * @param startTime
     * @param endTime
     * @return
     */
    int countUndoneTaskByClassIdAndTime(@Param("classId") int classId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
