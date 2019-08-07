package com.koolearn.club.dto.wechat;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lilong01 on 2018/3/14.
 */
@Getter
@Setter
public class BaseTemplateVo extends BaseSerialization{
    private short templateType;//模板类型
    private int userId; //用户ID（学生ID或老师ID）
    private short userType; //老师或学生（1老师2学生）
    private String page;//跳转页面
    private String templateId; //模板ID
}
