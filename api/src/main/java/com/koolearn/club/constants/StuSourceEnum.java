package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum StuSourceEnum {
    WECHAT((short)1, "微信");
    private short code;
    private String name;

    StuSourceEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(StuSourceEnum messageTypeEnum : StuSourceEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static StuSourceEnum getByCode(short code){
        for(StuSourceEnum messageTypeEnum : StuSourceEnum.values()){
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
