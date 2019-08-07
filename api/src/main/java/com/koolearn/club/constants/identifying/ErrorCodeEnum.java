package com.koolearn.club.constants.identifying;

public enum ErrorCodeEnum {

    NONE(-1, "未知错误"),
    IDENTIFYING_CODE_SAVE_FAIL(17000, "创建码获取失败"),
    IDENTIFYING_CODE_NOT_FOUND(17001, "未找到创建码"),
    IDENTIFYING_CODE_USEED(17002, "创建码已经被使用");


    private int code;
    private String message;

    private ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ErrorCodeEnum resolve(Integer code) {

        switch (code) {
            case 17000:
                return IDENTIFYING_CODE_SAVE_FAIL;
            default:
                return NONE;

        }
    }
}
