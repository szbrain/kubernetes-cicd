package com.koolearn.club.service;

import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.dto.message.TeachMessageDTO;
import com.koolearn.club.entity.PeTeachMessage;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public interface ITeachMessageService {

    PeTeachMessage getById(int id);


    /**
     * 根据频道ID查询消息列表
     * @param offset
     * @param pageSize
     * @param channelId
     * @return
     */
    PageDTO<TeachMessageDTO> listByChannelId(int offset, int pageSize, int channelId);

    /**
     * 发送消息（老师端）
     * @param type
     * @param businessId
     * @return
     */
    int sendMessage(short type, int businessId);
}
