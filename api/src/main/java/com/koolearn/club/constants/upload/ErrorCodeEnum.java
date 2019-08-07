package com.koolearn.club.constants.upload;

public enum ErrorCodeEnum {

    NONE(-1, "未知错误"),
    UPLOAD_CODE_SAVE_FAIL(18000, "上传失败"),
    UPLOAD_CODE_PARAM_ERROR(18001, "参数错误");


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
            case 18000:
                return UPLOAD_CODE_SAVE_FAIL;
            case 18001:
                return UPLOAD_CODE_PARAM_ERROR;
            default:
                return NONE;

        }
    }
}
