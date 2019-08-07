package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeTeachToken;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface TeachTokenMapper {
    PeTeachToken getById(int id);

    /**
     *  通过code查询
     * @param code
     * @return
     */
    PeTeachToken getByCode(String code);

    /**
     *  新增
     * @param teachToken
     * @return
     */
    int insert(PeTeachToken teachToken);

    /**
     * 更新
     * @param teachToken
     * @return
     */
    int update(PeTeachToken teachToken);

    /**
     *  更新状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(@Param("id") int id, @Param("status") short status);
}
