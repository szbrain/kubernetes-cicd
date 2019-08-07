package com.koolearn.club.constants.commons;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum LearningCommenTypeEnum {
    TEXT((short)1, "文字"),
    VOICE((short)2, "语音");
    private short code;
    private String name;

    LearningCommenTypeEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(LearningCommenTypeEnum learningCommenTypeEnum : LearningCommenTypeEnum.values()){
            if(learningCommenTypeEnum.code == code){
                return learningCommenTypeEnum.name;
            }
        }
        return null;
    }


    public static LearningCommenTypeEnum getByCode(short code){
        for(LearningCommenTypeEnum learningCommenTypeEnum : LearningCommenTypeEnum.values()){
            if(learningCommenTypeEnum.code == code){
                return learningCommenTypeEnum;
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
