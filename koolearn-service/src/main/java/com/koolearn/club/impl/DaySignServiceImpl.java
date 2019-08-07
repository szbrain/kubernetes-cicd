package com.koolearn.club.impl;

import com.koolearn.club.dto.daysign.DaySignDTO;
import com.koolearn.club.entity.PeDaySign;
import com.koolearn.club.entity.PeDaySignRecord;
import com.koolearn.club.mapper.DaySignMapper;
import com.koolearn.club.service.IDaySignService;
import com.koolearn.club.utils.Lunar;
import com.koolearn.util.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class DaySignServiceImpl implements IDaySignService {

    @Resource
    private DaySignMapper daySignMapper;

    @Override
    public PeDaySign getById(int id) {
        return daySignMapper.getById(id);
    }

    @Override
    @Transactional
    public DaySignDTO random() {
        DaySignDTO daySignDTO = new DaySignDTO();
        PeDaySign daySign = daySignMapper.getCurrentDaySign();
        if(null == daySign){ //当天未生成日签
            List<PeDaySign> daySignList = daySignMapper.listNotUse();
            Random random = new Random();
            int index = random.nextInt(daySignList.size());
            daySign = daySignList.get(index);
            //添加日签使用记录
            PeDaySignRecord daySignRecord = new PeDaySignRecord();
            daySignRecord.setCreateTime(new Date());
            daySignRecord.setDaySignId(daySign.getId());
            daySignMapper.insertRecord(daySignRecord);
        }
        BeanUtils.copyProperties(daySignDTO, daySign);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar today = Calendar.getInstance();
        Date date = new Date();
        today.setTime(date);
        Lunar lunar = new Lunar(today);//生成农历
        daySignDTO.setChineseDate(lunar.toString());
        daySignDTO.setDate(format.format(date));
        return daySignDTO;
    }


}
