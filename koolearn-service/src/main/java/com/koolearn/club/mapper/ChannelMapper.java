package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeChannel;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface ChannelMapper {
    PeChannel getById(int id);

    /**
     * 分页查询频道
     *
     * @param teachId
     * @param offset
     * @param pageSize
     * @return
     */
    List<PeChannel> listByTeachId(@Param("teachId") int teachId, @Param("offset") int offset, @Param("pageSize") int pageSize);

    /**
     * 查询总频道数
     * @return
     */
    int countByTeachId(int teachId);

    /**
     * 新建频道
     * @param channel
     * @return
     */
    int insert(PeChannel channel);

    /**
     * 更新频道内容
     * @param content
     * @param id
     * @return
     */
    int updateContent(@Param("content") String content, @Param("id") int id);

    /**
     * 根据业务ID 查询频道
     * @param businessId
     * @return
     */
    PeChannel getByBusinessId(int businessId);

    /**
     * 更新频道未读消息数量
     * @param id
     * @param num
     * @return
     */
    int updateUnreadCount(@Param("id") int id, @Param("num") int num);

    /**
     * 根据老师D 查询频道
     * @param teachId
     * @param type
     * @return
     */
    PeChannel getByTeachIdType(@Param("teachId") int teachId, @Param("type") short type);
}
