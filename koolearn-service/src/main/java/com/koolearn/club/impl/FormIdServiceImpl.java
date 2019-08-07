package com.koolearn.club.impl;

import com.koolearn.club.entity.PeClass;
import com.koolearn.club.entity.PeFormId;
import com.koolearn.club.mapper.FormIdMapper;
import com.koolearn.club.service.IFormIdService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class FormIdServiceImpl implements IFormIdService {

    @Resource
    private FormIdMapper formIdMapper;

    @Override
    public PeClass getById(int id) {
        return formIdMapper.getById(id);
    }

    @Override
    @Transactional
    public int saveFormIds(String openId, List<String> formIdList, short type) {
        int count = 0;
        for(String formId : formIdList){
            PeFormId peFormId = new PeFormId();
            peFormId.setOpenId(openId);
            peFormId.setFormId(formId);
            peFormId.setType(type);
            peFormId.setCreateTime(new Date());
            count += formIdMapper.insert(peFormId);
        }
        return count;
    }

    @Override
    public List<PeFormId> listByOpenId(String openId, short type) {
        List<PeFormId> formIdList = formIdMapper.listByOpenId(openId,type);
        return formIdList;
    }


    @Override
    public List<PeFormId> listOfEverydayByOpenId(String openId, short type, Date dateTime) {

        List<PeFormId> formIdList = formIdMapper.listOfEverydayByOpenId(openId,type,dateTime);
        return formIdList;
    }

    @Override
    @Transactional
    public int updateIsUse(String openId, String formId) {
        return formIdMapper.updateIsUse(openId, formId);
    }
}
