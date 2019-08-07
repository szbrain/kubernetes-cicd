package com.koolearn.club.constants.activity;

public enum ActivityErrorCodeEnum {

    NONE(-1, "未知错误"),
    ACTIVITY_SAVE_FAIL(19000, "活动创建失败"),
    ACTIVITY_NOT_FOUND(19001, "未找到该活动"),
    ACTIVITY_NO_AWARD_ITEM(19002, "活动缺少奖项"),
    ACTIVITY_CLOSED(19003, "活动已结束"),
    ACTIVITY_NO_ALLOWED(19004, "没有参与抽奖资格"),
    ACTIVITY_NO_RED_POCKET_CODE(19005, "红包验证码不能为空"),
    ACTIVITY_RED_POCKET_CODE_ERROR(19006, "红包验证码无效");


    private int code;
    private String message;

    private ActivityErrorCodeEnum(int code, String message) {
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

    public static ActivityErrorCodeEnum resolve(Integer code) {

        switch (code) {
            case 19000:
                return ACTIVITY_SAVE_FAIL;
            case 19001:
                return ACTIVITY_NOT_FOUND;
            case 19002:
                return ACTIVITY_NO_AWARD_ITEM;
            case 19003:
                return ACTIVITY_CLOSED;
            case 19004:
                return ACTIVITY_NO_ALLOWED;
            case 19005:
                return ACTIVITY_NO_RED_POCKET_CODE;
            case 19006:
                return ACTIVITY_RED_POCKET_CODE_ERROR;
            default:
                return NONE;

        }
    }
}
