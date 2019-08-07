package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum StuMessageTypeEnum {
    POST_TASK((short)1, "发布新任务"),
    POST_NOTICE((short)2, "发布班级公告"),
    COMMENT_LEARN((short)3, "点评学习总结"),
    PRAISE_LEARN((short)4, "表扬学习总结");
    private short code;
    private String name;

    StuMessageTypeEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(StuMessageTypeEnum stuMessageTypeEnum : StuMessageTypeEnum.values()){
            if(stuMessageTypeEnum.code == code){
                return stuMessageTypeEnum.name;
            }
        }
        return null;
    }


    public static StuMessageTypeEnum getByCode(short code){
        for(StuMessageTypeEnum stuMessageTypeEnum : StuMessageTypeEnum.values()){
            if(stuMessageTypeEnum.code == code){
                return stuMessageTypeEnum;
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
