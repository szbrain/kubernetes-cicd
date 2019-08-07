package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum FormIdTypeEnum {
    TEACH((short)1, "教师"),
    STU((short)2, "学生");
    private short code;
    private String name;

    FormIdTypeEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(FormIdTypeEnum messageTypeEnum : FormIdTypeEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static FormIdTypeEnum getByCode(short code){
        for(FormIdTypeEnum messageTypeEnum : FormIdTypeEnum.values()){
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
