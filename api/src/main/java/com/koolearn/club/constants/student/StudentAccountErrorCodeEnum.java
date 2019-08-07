package com.koolearn.club.constants.student;

public enum StudentAccountErrorCodeEnum {

    NONE(-1, "未知错误"),
    TRANSFERS_PAY_FAIL(25000, "提现失败"),
    ACCOUNT_BALANCE_NOT_ENOUGH(25001, "余额不足"),
    TRANC_AMOUNT_GE_1(25002, "提现金额需大于1元"),;


    private int code;
    private String message;

    private StudentAccountErrorCodeEnum(int code, String message) {
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

    public static StudentAccountErrorCodeEnum resolve(Integer code) {

        switch (code) {
            case 25000:
                return TRANSFERS_PAY_FAIL;
            case 25001:
                return ACCOUNT_BALANCE_NOT_ENOUGH;
            case 25002:
                return TRANC_AMOUNT_GE_1;
            default:
                return NONE;

        }
    }
}
