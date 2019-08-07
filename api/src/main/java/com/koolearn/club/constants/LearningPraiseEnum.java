package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum LearningPraiseEnum {
    LIKE((short)1, "点赞");
    private short code;
    private String name;

    LearningPraiseEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(LearningPraiseEnum messageTypeEnum : LearningPraiseEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static LearningPraiseEnum getByCode(short code){
        for(LearningPraiseEnum messageTypeEnum : LearningPraiseEnum.values()){
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
