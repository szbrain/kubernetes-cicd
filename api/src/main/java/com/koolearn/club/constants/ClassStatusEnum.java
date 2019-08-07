package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum ClassStatusEnum {
    WAIT((short)1,"待开课"),
    ONGOING((short)2, "进行中"),
    FINISH((short)3, "已结束");

    private short code;
    private String name;

    ClassStatusEnum(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(short code){
        for(ClassStatusEnum classStatusEnum : ClassStatusEnum.values()){
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
