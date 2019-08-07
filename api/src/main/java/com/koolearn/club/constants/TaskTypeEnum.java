package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum TaskTypeEnum {
    NORMAL((short)1, "标准"),
    LIVE((short)2, "直播");

    private short code;
    private String name;

    TaskTypeEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(TaskTypeEnum messageTypeEnum : TaskTypeEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static TaskTypeEnum getByCode(short code){
        for(TaskTypeEnum messageTypeEnum : TaskTypeEnum.values()){
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
