package com.koolearn.club.constants.award;

public enum AwardItemErrorCodeEnum {

    NONE(-1, "未知错误"),
    AWARD_ITEM_DELETE_FAIL(22000, "奖项删除失败"),
    AWARD_ITEM_NOT_FOUND(22001, "未找到该中奖项"),
    AWARD_ITEM_EDIT_FAIL(22002, "奖项修改失败");


    private int code;
    private String message;

    private AwardItemErrorCodeEnum(int code, String message) {
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

    public static AwardItemErrorCodeEnum resolve(Integer code) {

        switch (code) {
            case 22000:
                return AWARD_ITEM_DELETE_FAIL;
            case 22001:
                return AWARD_ITEM_NOT_FOUND;
            case 22002:
                return AWARD_ITEM_EDIT_FAIL;
            default:
                return NONE;

        }
    }
}
