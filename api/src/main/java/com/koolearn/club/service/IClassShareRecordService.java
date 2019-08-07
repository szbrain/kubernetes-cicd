package com.koolearn.club.service;

import com.koolearn.club.entity.PeClassShareRecord;

import java.util.List;

/**
 * 课程分享记录service
 * Created by lvyangjun on 2018/03/14.
 */
public interface IClassShareRecordService {


    /**
     * 获取分享课程的学生记录
     *
     * @param classId
     * @return
     */
    List<PeClassShareRecord> getList(int offset, int pageSize, int classId);

    /**
     * 获取分享课程的学生记录总数
     *
     * @param classId
     * @return
     */
     int getListCount(int classId);


    /**
     * 评价老师的课程
     * @param stuId
     * @param classId
     * @return
     */
    int save(int stuId, int classId);





}
