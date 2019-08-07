package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum CardTypeEnum {
    GAIN_CARD((short)1, "成就卡"),
    CLASS_CARD((short)2, "班级卡");
    private short code;
    private String name;

    CardTypeEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(CardTypeEnum messageTypeEnum : CardTypeEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static CardTypeEnum getByCode(short code){
        for(CardTypeEnum messageTypeEnum : CardTypeEnum.values()){
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
