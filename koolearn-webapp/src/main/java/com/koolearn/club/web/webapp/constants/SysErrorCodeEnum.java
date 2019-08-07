package com.koolearn.club.web.webapp.constants;


public enum SysErrorCodeEnum {


    NONE(-1, "未知错误"),

    ERR_SYSTEM(100001, "系统错误"),

    ERR_SERVICE_STOP(100002, "服务不可用"),

    ERR_AUTH_LIMIT(100003, "访问权限受限"),

    ERR_ILLEGAL_PARAM(100004, "参数值非法"),

    ERR_REST_FAIL(100005, "服务接口调用失败");


    private int code;
    private String message;

    private SysErrorCodeEnum(int code, String message) {
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

    public static SysErrorCodeEnum resolve(Integer code) {

        switch (code) {
            case 100001:
                return ERR_SYSTEM;
            case 100002:
                return ERR_SERVICE_STOP;
            case 100003:
                return ERR_AUTH_LIMIT;
            case 100004:
                return ERR_ILLEGAL_PARAM;
            default:
                return NONE;
        }
    }
}
