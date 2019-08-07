package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum SexTypeEnum {
    MALE((short)1, "男"),
    FEMALE((short)2, "女"),
    UNKNOWN((short)3,"未知");
    private short code;
    private String name;

    SexTypeEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(SexTypeEnum messageTypeEnum : SexTypeEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static SexTypeEnum getByCode(short code){
        for(SexTypeEnum messageTypeEnum : SexTypeEnum.values()){
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
