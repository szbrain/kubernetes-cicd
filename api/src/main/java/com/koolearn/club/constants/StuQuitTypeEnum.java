package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum StuQuitTypeEnum {
    ACTIVE((short)1, "主动"),
    REMOVE((short)2, "移除");
    private short code;
    private String name;

    StuQuitTypeEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(StuQuitTypeEnum messageTypeEnum : StuQuitTypeEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static StuQuitTypeEnum getByCode(short code){
        for(StuQuitTypeEnum messageTypeEnum : StuQuitTypeEnum.values()){
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
