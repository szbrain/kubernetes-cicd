package com.koolearn.club.constants.award;

public enum AwardErrorCodeEnum {

    NONE(-1, "未知错误"),
    AWARD_SAVE_FAIL(20000, "奖品创建失败"),
    AWARD_NOT_FOUND(20001, "未找到该奖品");


    private int code;
    private String message;

    private AwardErrorCodeEnum(int code, String message) {
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

    public static AwardErrorCodeEnum resolve(Integer code) {

        switch (code) {
            case 20000:
                return AWARD_SAVE_FAIL;
            case 20001:
                return AWARD_NOT_FOUND;
            default:
                return NONE;

        }
    }
}
