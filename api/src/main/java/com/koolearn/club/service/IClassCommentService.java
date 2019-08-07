package com.koolearn.club.service;

import com.koolearn.club.dto.classroom.*;
import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.entity.PeClass;
import com.koolearn.club.entity.PeClassComment;

import java.util.List;

/**
 * Created by lvyangjun on 2018/03/14.
 */
public interface IClassCommentService {


    /**
     * 评价老师的课程
     * @param stuId
     * @param classId
     * @return
     */
    PeClassComment get(int stuId, int classId);

    /**
     * 提交课程评价
     * @param stuId
     * @param classId
     * @param score
     * @param content
     */
    int commit( int stuId,int classId,int score, String content);

    /**
     * 评论列表
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    List<PeClassComment> listByClassId(int offset, int pageSize, int classId);

    /**
     *  查询学生评价数量
     * @param classId
     * @return
     */
    int countByClassId(int classId);


}
