package com.koolearn.club.constants.activity;

public enum RedPocketErrorCodeEnum {

    NONE(-1, "未知错误"),
    RED_POCKET_ADD_FAIL(23000, "红包规则保存失败"),
    RED_POCKET_NOT_FOUND(23001, "未找到该红包规则"),
    RED_POCKET_FINISH_OUT(23002, "今天的红包已经被领完"),
    RED_POCKET_RECORD_FAIL(23003, "红包抽奖保存失败"),
    RED_POCKET_RECORD_NOT_FOUND(23004, "没有红包中奖纪录");


    private int code;
    private String message;

    private RedPocketErrorCodeEnum(int code, String message) {
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

    public static RedPocketErrorCodeEnum resolve(Integer code) {

        switch (code) {
            case 23000:
                return RED_POCKET_ADD_FAIL;
            case 23001:
                return RED_POCKET_NOT_FOUND;
            case 23002:
                return RED_POCKET_FINISH_OUT;
            case 23003:
                return RED_POCKET_RECORD_FAIL;
            case 23004:
                return RED_POCKET_RECORD_NOT_FOUND;
            default:
                return NONE;

        }
    }
}
