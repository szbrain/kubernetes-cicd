package com.koolearn.club.constants;

/**
 * Created by lvyangjun on 2018/03/14.
 */
public enum ClassCommentStatusEnum {
    COMMENT_NO((short)1,"未评论"),
    COMMENT((short)2, "已评论");

    private short code;
    private String name;

    ClassCommentStatusEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(ClassCommentStatusEnum classStatusEnum : ClassCommentStatusEnum.values()){
            if(classStatusEnum.code == code){
                return classStatusEnum.name;
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
