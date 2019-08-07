package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeAssistant;
import com.koolearn.framework.mybatis.annotation.DAO;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface AssistantMapper {
    PeAssistant getById(int id);
}
