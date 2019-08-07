package com.koolearn.club.constants.commons;

/**
 * Created by lvyangjun on 2018/03/14.
 */
public enum CommentTypeEnum {
    COMMENT_TEXT((short)1,"文字评论"),
    COMMENT_VOICE((short)2, "语音评论");

    private short code;
    private String name;

    CommentTypeEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()){
            if(commentTypeEnum.code == code){
                return commentTypeEnum.name;
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
