package com.koolearn.club.dto.daysign;

import com.koolearn.club.entity.PeDaySign;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lilong01 on 2018/5/18.
 */
@Getter
@Setter
public class DaySignDTO extends PeDaySign{
    private String date;
    private String ChineseDate;
}
