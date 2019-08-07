package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeTeachMessage;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface TeachMessageMapper {
    PeTeachMessage getById(int id);

    /**
     * 根据频道ID 查询消息列表
     * @param offset
     * @param pageSize
     * @param channelId
     * @return
     */
    List<PeTeachMessage> listByChannelId(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("channelId") int channelId);

    /**
     *  根据频道ID 查询消息数
     * @param channelId
     * @return
     */
    int countByChannelId(int channelId);

    /**
     * 新增消息
     * @param teachMessage
     * @return
     */
    int insert(PeTeachMessage teachMessage);
}
