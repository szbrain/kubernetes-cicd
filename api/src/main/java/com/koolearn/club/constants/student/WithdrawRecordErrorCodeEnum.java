package com.koolearn.club.constants.student;

public enum WithdrawRecordErrorCodeEnum {

    NONE(-1, "未知错误"),
    SAVE_FAIL(26000, "提现记录保存失败");



    private int code;
    private String message;

    private WithdrawRecordErrorCodeEnum(int code, String message) {
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

    public static WithdrawRecordErrorCodeEnum resolve(Integer code) {

        switch (code) {
            case 26000:
                return SAVE_FAIL;
            default:
                return NONE;

        }
    }
}
