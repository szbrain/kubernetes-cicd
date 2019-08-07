package com.koolearn.club.service;

import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.dto.message.StuMessageDTO;
import com.koolearn.club.entity.PeStuMessage;

/**
 * Created by lilong01 on 2018/2/27.
 */
public interface IStuMessageService {

    PeStuMessage getById(int id);

    /**
     * 查询学生通知列表
     * @param offset
     * @param pageSize
     * @param stuId
     * @return
     */
    PageDTO<StuMessageDTO> listMessageForStu(int offset, int pageSize, int stuId,int classId);
    /**
     * 查询老师通知列表
     * @param offset
     * @param pageSize
     * @param classId
     * @return
     */
    PageDTO<StuMessageDTO> listMessageForTeach(int offset, int pageSize, int classId);

    /**
     * 发送消息
     * @param type
     * @param businessId
     * @return
     */
    int sendMessage(short type, int businessId);
}
