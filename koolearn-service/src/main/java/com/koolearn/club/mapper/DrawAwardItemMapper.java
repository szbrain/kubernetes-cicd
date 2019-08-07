package com.koolearn.club.mapper;


import com.koolearn.club.entity.PeDrawAwardItem;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@DAO
public interface DrawAwardItemMapper {


    /**
     * 查询所有奖项
     * @return
     */
    List<PeDrawAwardItem> queryAwardItem(int classId);

    boolean addAwardItem(PeDrawAwardItem peDrawAwardItem);

    boolean editAwardItem(PeDrawAwardItem peDrawAwardItem);

    /**
     * 查询所有奖项
     * @return
     */
    List<PeDrawAwardItem> queryAwardItemByActivityId(int activityId);


    /**
     * 查询所有奖项
     * @return
     */
    List<PeDrawAwardItem> queryAwardItemNoDefaulByActivityId(int activityId);

    /**
     * 查询奖项ById
     * @return
     */
    PeDrawAwardItem queryAwardItemById(@Param("id") int id);

    /**
     * 更新奖项库存
     * @param drawAwardItem
     * @return
     */
    int updateAwardItemTotalNum(PeDrawAwardItem drawAwardItem);


}