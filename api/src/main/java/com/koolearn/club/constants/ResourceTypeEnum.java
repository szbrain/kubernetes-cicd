package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum ResourceTypeEnum {

    IMAGE((short)1, "图片"),
    VOICE((short)2, "语音"),
    VIDEO((short)3, "视频");
    private short code;
    private String name;

    ResourceTypeEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(ResourceTypeEnum messageTypeEnum : ResourceTypeEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static ResourceTypeEnum getByCode(short code){
        for(ResourceTypeEnum messageTypeEnum : ResourceTypeEnum.values()){
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
