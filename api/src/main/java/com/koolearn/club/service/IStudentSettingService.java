package com.koolearn.club.service;

import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.entity.PeStudent;
import com.koolearn.club.entity.PeStudentSetting;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public interface IStudentSettingService {




    /**
     * 查询设置打卡提醒的学生
     * @param
     * @return
     */
    List<PeStudentSetting> getListHaveRemindTime();

}
