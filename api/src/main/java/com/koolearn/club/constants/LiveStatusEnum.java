package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum LiveStatusEnum {
    WAIT((short)1,"未开始"),
    ONGOING((short)2, "进行中"),
    FINISH((short)3, "已结束");
    private short code;
    private String name;

    LiveStatusEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(LiveStatusEnum messageTypeEnum : LiveStatusEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static LiveStatusEnum getByCode(short code){
        for(LiveStatusEnum messageTypeEnum : LiveStatusEnum.values()){
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
