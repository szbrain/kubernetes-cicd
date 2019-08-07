package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum LiveRedPocketRuleEnum {
    SAVED((short)1, "已保存"),
    ONGOING((short)2, "进行中"),
    END((short)3, "已结束");
    private short code;
    private String name;

    LiveRedPocketRuleEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(LiveRedPocketRuleEnum messageTypeEnum : LiveRedPocketRuleEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static LiveRedPocketRuleEnum getByCode(short code){
        for(LiveRedPocketRuleEnum messageTypeEnum : LiveRedPocketRuleEnum.values()){
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
