package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum SafeBoxTypeEnum {
    EXAMINEE((short)1, "准考证");
    private short code;
    private String name;

    SafeBoxTypeEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(SafeBoxTypeEnum messageTypeEnum : SafeBoxTypeEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static SafeBoxTypeEnum getByCode(short code){
        for(SafeBoxTypeEnum messageTypeEnum : SafeBoxTypeEnum.values()){
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
