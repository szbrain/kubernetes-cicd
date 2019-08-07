package com.koolearn.club.constants;

/**
 * 活动状态
 */
public enum ActivityStatusEnum {
    WAIT((short)0,"未开始"),
    ONGOING((short)1, "进行中"),
    ADVANCE_FINISHED((short)2, "提前结束"),
    FINISHED((short)3, "已结束");

    private short code;
    private String name;

    ActivityStatusEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(ActivityStatusEnum activityStatusEnum : ActivityStatusEnum.values()){
            if(activityStatusEnum.code == code){
                return activityStatusEnum.name;
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
