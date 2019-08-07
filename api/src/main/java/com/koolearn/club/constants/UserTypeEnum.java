package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum UserTypeEnum {
    TEACHER((short)1, "教师"),
    STUDENT((short)2, "学生");
    private short code;
    private String name;

    UserTypeEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(UserTypeEnum messageTypeEnum : UserTypeEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static UserTypeEnum getByCode(short code){
        for(UserTypeEnum messageTypeEnum : UserTypeEnum.values()){
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
