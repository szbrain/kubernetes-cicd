package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeClass;
import com.koolearn.club.entity.PeFormId;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface FormIdMapper {
    PeClass getById(int id);

    int insert(PeFormId peFormId);

    List<PeFormId> listByOpenId(@Param("openId") String openId, @Param("type") short type);

    List<PeFormId> listOfEverydayByOpenId(@Param("openId") String openId, @Param("type") short type, @Param("dateTime") Date dateTime);

    int updateIsUse(@Param("openId") String openId, @Param("formId") String formId);
}
