package com.koolearn.club.service;

import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.entity.PeStudent;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public interface IStudentService {

    PeStudent getById(int id);

    /**
     * 根据班级ID分页查询学生列表
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    PageDTO<PeStudent> listByClassId(int offset, int pageSize, int classId);

    /**
     * 根据班级ID查询学生列表
     * @param classId
     * @return
     */
    List<PeStudent> listAllByClassId(int classId);

    /**
     * 查询当前未完成任务的学生列表
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    PageDTO<PeStudent> listUndoneTaskByClassId(int offset, int pageSize, int classId);


    /**
     * 查询当前所有未完成任务的学生列表
     * @param classId
     * @return
     */
    List<PeStudent> listAllUndoneTaskByClassId(int classId);


    /**
     * 查询当天退出的学生列表
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    PageDTO<PeStudent> listQuitStuByClassId(int offset, int pageSize, int classId);

    /**
     * 移除学生
     * @param stuId
     * @param classId
     * @param rejoin
     * @return
     */
    int removeStudent(int stuId, int classId, boolean rejoin);

    /**
     * 创建学生
     * @param student
     * @return
     */
    int insertStudent(PeStudent student);

    /**
     * 编辑学生信息
     * @param student
     * @return
     */
    int editStudent(PeStudent student);

    /**
     * 根据sid查询学生
     * @param sid
     * @return
     */
    PeStudent getBySid(String sid);

    /**
     * 根据OpenId查询学生
     * @param openId
     * @return
     */
    PeStudent getByOpenId(String openId);

    /**
     * 判断学生是否在当前班级中
     * @param stuId
     * @param classId
     * @return
     */
    boolean isInClass(Integer stuId, Integer classId);

    /**
     * 设置结束打卡提醒时间
     * @param stuId
     * @param classId
     *@param duration  @return
     */
    int setRemind(int stuId, int classId, int duration);

    /**
     * 获取打卡提醒时间
     * @param stuId
     * @param classId
     * @return
     */
    int getRemindTime(int stuId, int classId);
}
