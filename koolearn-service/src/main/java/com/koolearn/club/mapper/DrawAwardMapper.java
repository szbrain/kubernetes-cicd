package com.koolearn.club.mapper;


import com.koolearn.club.dto.award.AwardEditReqDTO;
import com.koolearn.club.dto.award.AwardListReqDTO;
import com.koolearn.club.entity.PeDrawAward;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@DAO
public interface DrawAwardMapper {


    /**
     * 根据id获取奖品
     * @param id
     * @return
     */
    PeDrawAward queryAwardById(Integer id);

    int addAward(PeDrawAward drawAward);

    int editAward(AwardEditReqDTO awardEditReqDTO);

    List<PeDrawAward> getAwardListByTeach(AwardListReqDTO awardListReqDTO);

    int getAwardListCountByTeach(AwardListReqDTO awardListReqDTO);

    int updateGrantCount(Integer awardId);

    List<PeDrawAward> simpleActivityList(@Param("teachId") Integer teachId);
}