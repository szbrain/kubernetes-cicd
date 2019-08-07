package com.koolearn.club.service;

import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.dto.message.ChannelDTO;
import com.koolearn.club.entity.PeChannel;

/**
 * Created by lilong01 on 2018/2/27.
 */
public interface IChannelService {
    PeChannel getById(int id);

    /**
     * 分页查询频道列表
     *
     * @param teachId
     * @param offset
     * @param pageSize
     * @return
     */
    PageDTO<ChannelDTO> listByTeachId(int teachId, int offset, int pageSize);

    /**
     * 创建频道
     * @param type
     * @param businessId
     * @return
     */
    int createChannel(short type, int businessId);
}
