package com.koolearn.club.constants.student;

public enum StudentErrorCodeEnum {

    NONE(-1, "未知错误"),
    STUDENT_NOT_FOUND(23000, "未找到该用户");


    private int code;
    private String message;

    private StudentErrorCodeEnum(int code, String message) {
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

    public static StudentErrorCodeEnum resolve(Integer code) {

        switch (code) {
            case 23000:
                return STUDENT_NOT_FOUND;
            default:
                return NONE;

        }
    }
}
