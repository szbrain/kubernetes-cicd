package com.koolearn.club.service;

import com.koolearn.club.entity.PeClass;
import com.koolearn.club.entity.PeFormId;

import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public interface IFormIdService {

    PeClass getById(int id);

    /**
     * 批量保存formId
     * @param openId
     * @param formIds
     * @param type
     * @return
     */
    int saveFormIds(String openId, List<String> formIds, short type);

    /**
     * 根据openId查询formId列表
     * @param openId
     * @param type
     * @return
     */
    List<PeFormId> listByOpenId(String openId, short type);

    /**
     * 根据openId查询每天的formId列表
     * @param openId
     * @param type
     * @return
     */
    List<PeFormId> listOfEverydayByOpenId(String openId, short type, Date dateTime);

    /**
     * 更新isUse
     * @param openId
     * @param formId
     * @return
     */
    int updateIsUse(String openId, String formId);
}
