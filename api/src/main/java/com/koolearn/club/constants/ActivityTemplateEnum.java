package com.koolearn.club.constants;

/**
 * 活动页模板
 */
public enum ActivityTemplateEnum {
    TURNTABLE((short)1,"转盘"),
    ENVELOPE((short)2, "信封");

    private short code;
    private String name;

    ActivityTemplateEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(ActivityTemplateEnum activityTriggerRuleEnum : ActivityTemplateEnum.values()){
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
