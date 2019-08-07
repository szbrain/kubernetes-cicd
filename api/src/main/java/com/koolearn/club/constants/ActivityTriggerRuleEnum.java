package com.koolearn.club.constants;

/**
 * 活动触发规则
 */
public enum ActivityTriggerRuleEnum {
    CARD((short)1,"打卡"),
    SHARE((short)2, "分享成就卡"),
    JOIN_CLASS((short)3, "加入班级"),
    COMMENT_ACTIVITY((short)4, "活动点评"),
    GET_HONOR((short)5, "获得勋章"),
    GET_PRAISE((short)6, "获得点赞");

    private short code;
    private String name;

    ActivityTriggerRuleEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(ActivityTriggerRuleEnum activityTriggerRuleEnum : ActivityTriggerRuleEnum.values()){
            if(activityTriggerRuleEnum.code == code){
                return activityTriggerRuleEnum.name;
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
