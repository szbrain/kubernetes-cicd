package com.koolearn.club.service;

import com.koolearn.club.dto.classroom.*;
import com.koolearn.club.dto.classroom.manage.ClassListParamDTO;
import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.entity.PeClass;
import com.koolearn.club.entity.PeClassStat;
import com.koolearn.club.entity.PeResource;
import com.koolearn.club.entity.PeStudent;

import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public interface IClassService {

    PeClass getById(int id);

    /**
     * 查询学生加入的班级列表
     * @param offset
     * @param pageSize
     * @param stuId
     * @return
     */
    PageDTO<ClassListDTO> listClassForStu(int offset, int pageSize, int stuId);

    /**
     * 查询班级详情(学生端)
     * @param classId
     * @return
     */
    ClassDetailDTO getClassForStu(int classId);

    /**
     * 加入班级
     * @param classId
     * @param stuId
     */
    void joinClass(int classId, int stuId);

    /**
     * 加入班级
     * @param classId
     * @param stuId
     */
    boolean checkJoinClass(int classId, int stuId);



    /**
     * 退出班级
     * @param classId
     * @param stuId
     */
    void exitClass(int classId, int stuId);

    /**
     * 评论班级课程
     * @param classId
     * @param content
     *//*
    void commentClass(int classId, int content);*/

    /**
     *  查询老师创建的班级列表
     * @param offset
     * @param pageSize
     * @param teachId
     * @return
     */
    PageDTO<ClassListDTO> listClassForTeach(int offset, int pageSize, int teachId);

    /**
     * 创建班级
     * @param createClassDTO
     * @return
     */
    int createClass(CreateClassDTO createClassDTO);

    /**
     *  查询班级详情(老师端)
     * @param classId
     * @return
     */
    ClassDetailDTO getClassForTeach(int classId);

    /**
     * 修改班级
     * @param editClassDTO
     * @return
     */
    int editClass(EditClassDTO editClassDTO);

    /**
     * 查询学生评价列表
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    PageDTO<ClassMarkListDTO> listClassMark(int offset, int pageSize, int classId);

    /**
     *  发布班级公告
     * @param classId
     * @param teachId
     * @param content
     * @param resourceList
     * @return
     */
    int postNotice(int classId, int teachId, String content, List<PeResource> resourceList);

    /**
     * 班级统计当天数据
     * @param classId
     * @return
     */
    ClassStatDTO statClass(int classId);

    /**
     * 结束班级
     * @param classId
     * @param teachId
     * @return
     */
    int finishClass(int classId, int teachId);

    /**
     *
     * @return
     */
    List<PeClass> allClassesByStatus(Short status);

    /**
     *  查询班级公告列表
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    PageDTO<ClassNoticeDTO> listClassNoticeForStu(int offset, int pageSize, int classId);

    /**
     * 查询指定日期下班级报表
     * @param yesterday
     * @return
     */
    List<PeClassStat> allClassStatsByDateWechatNocie(Date yesterday);

    /**
     *
     *  更新班级报表的状态微信通知状态为已发送
     * @param classStatsId
     * @return
     */
    int updateClassStatsWechatNotice(int classStatsId);

    /**
     * 编辑班级公告
     * @param noticeId
     * @param teachId
     * @param content
     * @param resourceList
     * @return
     */
    int editNotice(int noticeId, int teachId, String content, List<PeResource> resourceList);

    /**
     *  查询班级列表（manage）
     * @param classListParamDTO
     * @return
     */
    PageDTO<com.koolearn.club.dto.classroom.manage.ClassListDTO> listClassForManage(ClassListParamDTO classListParamDTO);


    /**
     * 班级报表统计
     * @param classStatReqDTO
     * @return
     */
    ClassStatRespDTO statClassForWebapp(ClassStatReqDTO classStatReqDTO);


    /**
     * 班级统计完成任务的学生
     * @param classStatDoneStuListReqDTO
     * @return
     */
    PageDTO<ClassStatDoneStuListRespDTO> listClassStatDoneStu(ClassStatDoneStuListReqDTO classStatDoneStuListReqDTO);

    /**
     *  班级统计完成未任务的学生
     * @param classStatRemindUndoneStuReqDTO
     * @return
     */
    List<Integer> listClassStatUndoneStu(ClassStatRemindUndoneStuReqDTO classStatRemindUndoneStuReqDTO);
}
