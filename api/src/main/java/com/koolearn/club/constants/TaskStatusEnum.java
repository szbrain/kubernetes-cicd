package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum TaskStatusEnum {
    POSTED((short)1, "已发布");
    private short code;
    private String name;

    TaskStatusEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(TaskStatusEnum messageTypeEnum : TaskStatusEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static TaskStatusEnum getByCode(short code){
        for(TaskStatusEnum messageTypeEnum : TaskStatusEnum.values()){
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
