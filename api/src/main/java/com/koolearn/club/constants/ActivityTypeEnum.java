package com.koolearn.club.constants;

/**
 * 活动类型
 */
public enum ActivityTypeEnum {
    DRAW_AWARD((short)1,"抽奖"),
    SHARE((short)2, "分享裂变"),
    COLLAGE((short)3, "拼团拼课"),
    RED_PACKE((short)4, "红包福利");

    private short code;
    private String name;

    ActivityTypeEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(ActivityTypeEnum activityTypeEnum : ActivityTypeEnum.values()){
            if(activityTypeEnum.code == code){
                return activityTypeEnum.name;
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
