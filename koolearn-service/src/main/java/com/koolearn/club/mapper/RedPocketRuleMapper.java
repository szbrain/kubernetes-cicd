package com.koolearn.club.mapper;


import com.koolearn.club.entity.PeRedPocketRule;
import com.koolearn.framework.mybatis.annotation.DAO;

@DAO
public interface RedPocketRuleMapper {


    Integer addRedPocketRule(PeRedPocketRule redPocketRule);

    PeRedPocketRule findById(Integer id);

    PeRedPocketRule findByActivityId(Integer activityId);

    Integer editRedPocketRule(PeRedPocketRule redPocketRule);


}