package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeClassContent;
import com.koolearn.framework.mybatis.annotation.DAO;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface ClassContentMapper {
    PeClassContent getById(int id);

    /**
     *  新增班级内容
     * @param classContent
     * @return
     */
    int insert(PeClassContent classContent);

    /**
     * 编辑班级内容
     * @param classContent
     * @return
     */
    int update(PeClassContent classContent);
}
