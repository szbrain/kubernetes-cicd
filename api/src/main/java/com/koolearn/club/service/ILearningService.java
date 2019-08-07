package com.koolearn.club.service;

import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.dto.learning.LearningDTO;
import com.koolearn.club.dto.learning.LearningResourceDTO;
import com.koolearn.club.entity.PeLearning;

import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public interface ILearningService {

    PeLearning getById(int id);

    /**
     * 未完成动态-学生
     * @param stuId
     * @param classId
     *
     * @return
     */
    LearningDTO learning(int stuId ,int classId);

    /**
     * 学习动态列表-学生其他学员
     *
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    PageDTO<LearningDTO> otherLearningHistory(int offset, int pageSize,int stuId, int classId, Date dateTime);

    /**
     * 学习动态列表-学生其他学员
     *
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    PageDTO<LearningDTO> otherLearningHistory(int offset, int pageSize,int stuId, int classId, Date dateTime,Long timeline);


    /**
     * 学习动态列表-学生自己
     * @param stuId
     * @param classId
     * @param dateTime
     * @return
     */
    List<LearningDTO> listHistoryForStu(int stuId, int classId, Date dateTime);

    /**
     * 学习动态列表-学生自己
     * @param stuId
     * @param classId
     * @return
     */
    PageDTO<LearningDTO> listHistoryForStu(int offset, int pageSize,int stuId, int classId);

    PageDTO<LearningDTO> listHistoryForStu(int offset, int pageSize, Integer stuId, String sid, int classId);

    /**
     * 学习动态列表-tech
     *
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    PageDTO<LearningDTO> listHistoryForTech(int offset, int pageSize, int classId, Date dateTime, Long timeline);


    /**
     * 学习动态列表-tech
     *
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    PageDTO<LearningDTO> listHistoryForTech(int offset, int pageSize, int classId, Date dateTime);

    /**
     * 开始学习
     *
     * @param stuId
     * @param classId
     * @return
     */
    Object startLearning(int stuId, int classId);

    /**
     * 结束学习
     * @param learningResourceDTO
     */
    int finishLearning(LearningResourceDTO learningResourceDTO);

    /**
     * 赞扬学习总结
     *
     * @param id
     * @param praiseType
     * @return
     */
    int praiseLearning(int id, short praiseType);

    /**
     * 点评学习总结
     *
     * @param id
     * @param type
     * @param content
     * @return
     */
    int commentLearning(int id, short type, String content,int duration);

    /**
     * 删除学习总结
     *
     * @param id
     * @return
     */
    int deleteLearning(int id);

    int deleteLearning(int id, int stuId);

    /**
     * 学生点赞
     * @param stuId
     * @param learningId
     * @return
     */
    int praiseLearningForStu(int stuId, int learningId);

    /**
     * 统计打卡人数
     * @param classId
     * @param statDate
     * @return
     */
    int countLearning(int classId, Date statDate);


    /**
     * 更新为已经提醒
     * @param id
     * @return
     */
    int updateIsReminded(int id);

    /**
     * 学生打卡
     * @param learningResourceDTO
     */
    int checkin(LearningResourceDTO learningResourceDTO);

    /**
     *  根据学生ID 查询打卡动态
     * @param stuId
     * @return
     */
    int deleteLearningByStuId(int stuId);

}
