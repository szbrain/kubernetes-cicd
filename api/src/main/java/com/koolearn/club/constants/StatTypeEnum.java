package com.koolearn.club.constants;

/**
 * Created by lilong01 on 2018/2/28.
 */
public enum StatTypeEnum {
    JOIN((short)1),
    QUIT((short)2),
    TASK_UNDONE((short)3);
    private short code;

    StatTypeEnum(short code) {
        this.code = code;
    }

    public short getCode(){
        return code;
    }
}
