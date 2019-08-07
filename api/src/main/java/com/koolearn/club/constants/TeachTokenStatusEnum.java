package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum TeachTokenStatusEnum {
    CODE((short)1, "生成code"),
    TOKEN((short)2, "生成token"),
    LOGIN((short)3, "已登录");
    private short code;
    private String name;

    TeachTokenStatusEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(TeachTokenStatusEnum messageTypeEnum : TeachTokenStatusEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static TeachTokenStatusEnum getByCode(short code){
        for(TeachTokenStatusEnum messageTypeEnum : TeachTokenStatusEnum.values()){
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
