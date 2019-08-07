package com.koolearn.club.constants.member;

public enum MemberManageErrorCodeEnum {

    NONE(-1, "未知错误"),
    MEMBER_REMOVE_FAIL(23000, "移除失败");


    private int code;
    private String message;

    private MemberManageErrorCodeEnum(int code, String message) {
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

    public static MemberManageErrorCodeEnum resolve(Integer code) {

        switch (code) {
            case 23000:
                return MEMBER_REMOVE_FAIL;
            default:
                return NONE;

        }
    }
}
