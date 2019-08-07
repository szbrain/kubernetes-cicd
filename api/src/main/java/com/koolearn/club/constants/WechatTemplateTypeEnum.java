package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum WechatTemplateTypeEnum {
    REMIND((short)1, "结束打卡提醒模板");
    private short code;
    private String name;

    WechatTemplateTypeEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(WechatTemplateTypeEnum messageTypeEnum : WechatTemplateTypeEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static WechatTemplateTypeEnum getByCode(short code){
        for(WechatTemplateTypeEnum messageTypeEnum : WechatTemplateTypeEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum;
            }
        }
        return null;
    }

    public String getName(){
        return name;
    }

    public short getCode(){
        return code;
    }

}
