package com.koolearn.club.mapper;

import com.koolearn.club.dto.learning.LearningHistoryReqDTO;
import com.koolearn.club.entity.PeLearning;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface LearningMapper {
    PeLearning getById(int id);


    /**
     * 未完成学习动态
     * @param stuId
     * @param classId
     * @return
     */
    PeLearning learning(@Param("stuId") int stuId, @Param("classId") int classId);

    /**
     * 打卡动态列表--学生自己
     * @param stuId
     * @param classId
     * @param dateTime
     * @return
     */
    List<PeLearning> listHistoryForStu(@Param("stuId") int stuId,
                                       @Param("classId") int classId,
                                       @Param("dateTime") Date dateTime);


    /**
     * 打卡动态列表--学生自己
     * @param stuId
     * @param classId
     * @return
     */
    List<PeLearning> listHistoryForStuOfV2(@Param("offset") int offset,
                                           @Param("pageSize") int pageSize,
                                           @Param("stuId") int stuId,
                                           @Param("classId") int classId);

    int countHistoryForStuOfV2(@Param("stuId") int stuId, @Param("classId") int classId);



    /**
     * 打卡动态列表--学生其他学员
     * @param offset
     * @param pageSize
     *
     * @param classId
     * @param dateTime
     * @return
     */
    List<PeLearning> otherLearningHistory(@Param("offset") int offset,
                                          @Param("pageSize") int pageSize,
                                          @Param("stuId") int stuId,
                                          @Param("classId") int classId,
                                          @Param("dateTime") Date dateTime,
                                          @Param("dateLine") Date dateLine);


    /**
     * 打卡动态列表--学生其他学员
     * @param offset
     * @param pageSize
     *
     * @param classId
     * @param dateTime
     * @return
     */
    List<PeLearning> otherLearningHistoryOfV1(@Param("offset") int offset,
                                              @Param("pageSize") int pageSize,
                                              @Param("stuId") int stuId,
                                              @Param("classId") int classId,
                                              @Param("dateTime") Date dateTime);

    /**
     *  查询打卡动态列表--老师
     * @param offset
     * @param pageSize
     * @param classId
     * @param dateTime
     * @return
     */
    List<PeLearning> listHistoryForTech(@Param("offset") int offset,
                                        @Param("pageSize") int pageSize,
                                        @Param("classId") int classId,
                                        @Param("dateTime") Date dateTime,
                                        @Param("dateLine") Date dateLine);


    /**
     *  查询打卡动态列表--老师
     * @param offset
     * @param pageSize
     * @param classId
     * @param dateTime
     * @return
     */
    List<PeLearning> listHistoryForTechOfV1(@Param("offset") int offset,
                                            @Param("pageSize") int pageSize,
                                            @Param("classId") int classId,
                                            @Param("dateTime") Date dateTime);


    /**
     * 学生打卡记录
     * @param learningHistoryReqDTO
     * @return
     */
    List<PeLearning> studentLearingHistory(LearningHistoryReqDTO learningHistoryReqDTO);


    /**
     * 学生打卡记录count
     * @param learningHistoryReqDTO
     * @return
     */
    Integer studentLearingHistoryCount(LearningHistoryReqDTO learningHistoryReqDTO);

    /**
     * 查询打卡动态数量--其他学生
     * @param classId
     * @return
     */
    int countOtherLearningHistory(@Param("stuId") int stuId,
                                  @Param("classId") int classId,
                                  @Param("dateTime") Date dateTime,
                                  @Param("dateLine") Date dateLine);

    /**
     * 查询打卡动态数量--其他学生
     * @param classId
     * @return
     */
    int countOtherLearningHistoryOfV1(@Param("stuId") int stuId,
                                      @Param("classId") int classId,
                                      @Param("dateTime") Date dateTime);




    /**
     * 查询打卡动态数量--老师
     * @param classId
     * @param dateTime
     * @return
     */
    int countHistoryForTech(@Param("classId") int classId, @Param("dateTime") Date dateTime, @Param("dateLine") Date dateLine);

    /**
     * 查询打卡动态数量--老师
     * @param classId
     * @param dateTime
     * @return
     */
    int countHistoryForTechOfV1(@Param("classId") int classId, @Param("dateTime") Date dateTime);

    /**
     * 新增打卡记录
     * @param learning
     * @return
     */
    Integer insert(PeLearning learning);

    /**
     * 通过班级ID，学生ID 查询打卡记录
     * @param stuId
     * @param classId
     * @return
     */
    PeLearning getByStuIdClassId(@Param("stuId") int stuId, @Param("classId") int classId);

    /**
     * 更新打卡记录
     * @param sign
     */
    int update(PeLearning sign);

    /**
     * 更新是否表扬
     * @param id
     * @param praiseType
     * @return
     */
    int updateIsPraise(@Param("id") int id, @Param("praiseType") short praiseType);

    /**
     *  更新是否点评
     * @param id
     * @return
     */
    int updateIsComment(int id);

    /**
     *  删除学习总结
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 统计学生学习的日期
     * @param stuId
     * @param classId
     * @return
     */
    List<String> listForStuLearning(@Param("stuId") int stuId, @Param("classId") int classId);

    /**
     * 统计打卡人数
     * @param classId
     * @param statDate
     * @return
     */
    int countLearning(@Param("classId") int classId, @Param("statDate") Date statDate);

    /**
     * 更新为已经提醒
     * @param id
     * @return
     */
    int updateIsReminded(int id);

    /**
     * 根据学生ID 查询打卡动态
     * @param stuId
     * @return
     */
    List<PeLearning> listByStuId(int stuId);
}
