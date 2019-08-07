package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum SignStatusEnum {
    START((short)1, "已开始"),
    END((short)2, "已结束");
    private short code;
    private String name;

    SignStatusEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(SignStatusEnum messageTypeEnum : SignStatusEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static SignStatusEnum getByCode(short code){
        for(SignStatusEnum messageTypeEnum : SignStatusEnum.values()){
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
