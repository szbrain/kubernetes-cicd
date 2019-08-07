package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeStuMessage;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface StuMessageMapper {
    PeStuMessage getById(int id);

    /**
     * 查询学生通知列表
     * @param offset
     * @param pageSize
     * @param stuId
     * @return
     */
    List<PeStuMessage> listMessageForStu(@Param("offset") int offset,
                                         @Param("pageSize") int pageSize,
                                         @Param("stuId") int stuId,
                                         @Param("classId") int classId);


    /**
     * 查询学生通知数量
     * @param stuId
     * @return
     */
    int countMessageForStu(int stuId);

    /**
     *  查询老师通知列表
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    List<PeStuMessage> listMessageForTeach(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("classId") int classId);

    /**
     * 查询老师通知数量
     * @param classId
     * @return
     */
    int countMessageForTeach(int classId);

    /**
     * 创建消息
     * @param stuMessage
     * @return
     */
    int insert(PeStuMessage stuMessage);

    /**
     * 批量创建消息
     * @param stuMessageList
     * @return
     */
    int batchInsert(List<PeStuMessage> stuMessageList);

    PeStuMessage getByStuIdClassIdBusinessId(@Param("stuId") int stuId, @Param("classId") int classId, @Param("businessId") int businessId, @Param("type") short type);
}
