package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeDaySign;
import com.koolearn.club.entity.PeDaySignRecord;
import com.koolearn.framework.mybatis.annotation.DAO;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface DaySignMapper {
    PeDaySign getById(int id);

    /**
     * 查询当月未使用的日签
     * @return
     */
    List<PeDaySign> listNotUse();

    /**
     * 添加日签使用记录
     * @param daySignRecord
     * @return
     */
    int insertRecord(PeDaySignRecord daySignRecord);

    /**
     * 获取当天的日签
     * @return
     */
    PeDaySign getCurrentDaySign();
}
