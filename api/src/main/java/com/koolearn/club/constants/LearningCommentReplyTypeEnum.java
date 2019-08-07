package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum LearningCommentReplyTypeEnum {
    STUDENT((short)1, "学生回复教师"),
    TEACHER((short)2,"教师回复学生");
    private short code;
    private String name;

    LearningCommentReplyTypeEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(LearningCommentReplyTypeEnum messageTypeEnum : LearningCommentReplyTypeEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static LearningCommentReplyTypeEnum getByCode(short code){
        for(LearningCommentReplyTypeEnum messageTypeEnum : LearningCommentReplyTypeEnum.values()){
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
