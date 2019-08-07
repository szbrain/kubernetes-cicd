package com.koolearn.club.constants.award;

public enum AwardRecordErrorCodeEnum {

    NONE(-1, "未知错误"),
    AWARD_RECORD_SAVE_FAIL(21000, "更新联系方式失败"),
    AWARD_RECORD_NOT_FOUND(21001, "未找到该中奖纪录");


    private int code;
    private String message;

    private AwardRecordErrorCodeEnum(int code, String message) {
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

    public static AwardRecordErrorCodeEnum resolve(Integer code) {

        switch (code) {
            case 21000:
                return AWARD_RECORD_SAVE_FAIL;
            case 21001:
                return AWARD_RECORD_NOT_FOUND;
            default:
                return NONE;

        }
    }
}
