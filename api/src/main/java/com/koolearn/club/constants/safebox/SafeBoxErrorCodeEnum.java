package com.koolearn.club.constants.safebox;

public enum SafeBoxErrorCodeEnum {

    NONE(-1, "未知错误"),
    SAFE_BOX_SAVE_FAIL(28000, "保险箱保存失败"),
    SAFE_BOX_NOT_FOUND(28001, "未找到该保险箱");

    private int code;
    private String message;

    private SafeBoxErrorCodeEnum(int code, String message) {
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

    public static SafeBoxErrorCodeEnum resolve(Integer code) {

        switch (code) {
            case 28000:
                return SAFE_BOX_SAVE_FAIL;
            case 28001:
                return SAFE_BOX_NOT_FOUND;
            default:
                return NONE;
        }
    }
}
