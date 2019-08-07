package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum TeachMessageTypeEnum {
    CREATE_CLASS((short)1, "创建班级"),
    LEARN((short)2, "学习总结"),
    POST_TASK((short)3, "学习任务"),
    STAT_STU((short)4, "学习人数统计"),
    CLASS_NOTICE((short)5, "班级公告"),
    NEW_JOIN((short)6, "新增学员"),
    STAT_CLASS((short)7, "班级学习统计");

    private short code;
    private String name;

    TeachMessageTypeEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(TeachMessageTypeEnum messageTypeEnum : TeachMessageTypeEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static TeachMessageTypeEnum getByCode(short code){
        for(TeachMessageTypeEnum messageTypeEnum : TeachMessageTypeEnum.values()){
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
