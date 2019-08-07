package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum ChannelTypeEnum {
    CLASSROOM((short)1, "班级频道"),
    ADD_STU((short)2, "新增学员"),
    LEARN_STAT((short)3, "班级学习报表");
    private short code;
    private String name;

    ChannelTypeEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(ChannelTypeEnum messageTypeEnum : ChannelTypeEnum.values()){
            if(messageTypeEnum.code == code){
                return messageTypeEnum.name;
            }
        }
        return null;
    }


    public static ChannelTypeEnum getByCode(short code){
        for(ChannelTypeEnum messageTypeEnum : ChannelTypeEnum.values()){
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
