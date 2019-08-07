package com.koolearn.club.service;

import com.koolearn.club.dto.daysign.DaySignDTO;
import com.koolearn.club.entity.PeDaySign;

/**
 * Created by lilong01 on 2018/2/27.
 */
public interface IDaySignService {
    PeDaySign getById(int id);

    /**
     * 随机生成日签
     * @return
     */
    DaySignDTO random();
}
