package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeRefuelPosterKeywords;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface RefuelPosterKeywordsMapper {
    PeRefuelPosterKeywords getById(int id);

    /**
     * 查询加油海报关键字
     * @param keywordIdList
     * @return
     */
    List<PeRefuelPosterKeywords> listKeywords(@Param("keywordIdList") List keywordIdList);

}
