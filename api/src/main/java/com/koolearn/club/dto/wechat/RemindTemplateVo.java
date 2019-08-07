package com.koolearn.club.dto.wechat;

import com.google.common.collect.Maps;
import com.koolearn.club.constants.WechatTemplateTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created by lilong01 on 2018/4/4.
 */
@Getter
@Setter
public class RemindTemplateVo extends BaseTemplateVo{
    private String planName;
    private String markedWords;

    public RemindTemplateVo(){
        super.setTemplateType(WechatTemplateTypeEnum.REMIND.getCode());
    }

    public Map getDataMap(){
        Map dataMap = Maps.newConcurrentMap();
        Map keyword1 = Maps.newConcurrentMap();
        keyword1.put("value", planName);//计划名
        keyword1.put("color", "#000000");
        Map keyword2 = Maps.newConcurrentMap();
        keyword2.put("value", markedWords);//提示语
        keyword2.put("color", "#000000");
        dataMap.put("keyword1", keyword1);
        dataMap.put("keyword2", keyword2);
        return dataMap;
    }

}
