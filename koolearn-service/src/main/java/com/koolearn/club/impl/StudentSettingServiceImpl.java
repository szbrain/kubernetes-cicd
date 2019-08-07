package com.koolearn.club.impl;

import com.koolearn.club.entity.PeStudentSetting;
import com.koolearn.club.mapper.StudentSettingMapper;
import com.koolearn.club.service.IStudentSettingService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lvyangjun on 2018/4/3.
 */
public class StudentSettingServiceImpl implements IStudentSettingService {

    @Resource
    private StudentSettingMapper studentSettingMapper;
    @Override
    public List<PeStudentSetting> getListHaveRemindTime() {
        List<PeStudentSetting> studentSettingList=studentSettingMapper.getListHaveRemindTime();
        return studentSettingList;
    }
}
