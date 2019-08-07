package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeRefuelPoster;
import com.koolearn.framework.mybatis.annotation.DAO;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface RefuelPosterMapper {
    PeRefuelPoster getById(int id);

    /**
     * 新增
     * @param refuelPoster
     * @return
     */
    int insert(PeRefuelPoster refuelPoster);

    /**
     * 通过学生ID 查询一条加油海报
     * @param stuId
     * @return
     */
    PeRefuelPoster getByStuId(int stuId);
}
