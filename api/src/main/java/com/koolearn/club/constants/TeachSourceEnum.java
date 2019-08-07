package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum TeachSourceEnum {
    WECHAT((short)1, "微信");
    private short code;
    private String name;

    TeachSourceEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(TeachSourceEnum messageTypeEnum : TeachSourceEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static TeachSourceEnum getByCode(short code){
        for(TeachSourceEnum messageTypeEnum : TeachSourceEnum.values()){
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
