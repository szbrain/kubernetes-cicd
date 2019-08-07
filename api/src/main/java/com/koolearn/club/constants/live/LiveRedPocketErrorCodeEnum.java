package com.koolearn.club.constants.live;

public enum LiveRedPocketErrorCodeEnum {

    NONE(-1, "未知错误"),
    LIVE_RED_POCKET_ADD_FAIL(27000, "直播红包保存失败"),
    LIVE_RED_POCKET_NOT_FOUND(27001, "未找到该直播红包"),
    LIVE_RED_POCKET_FINISH_OUT(27002, "今天的直播红包已经被领完"),
    LIVE_RED_POCKET_RECORD_FAIL(27003, "直播红包抽奖保存失败"),
    LIVE_RED_POCKET_RECORD_NOT_FOUND(27004, "没有直播红包中奖纪录"),
    LIVE_RED_POCKET_RECORD_NOT_START(27005, "直播红包未发放"),
    LIVE_RED_POCKET_RECORD_END(27006, "直播红包已经结束"),
    LIVE_RED_POCKET_RECORD_HAVE_GET(27007, "已经领过该直播红包");


    private int code;
    private String message;

    private LiveRedPocketErrorCodeEnum(int code, String message) {
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

    public static LiveRedPocketErrorCodeEnum resolve(Integer code) {

        switch (code) {
            case 27000:
                return LIVE_RED_POCKET_ADD_FAIL;
            case 27001:
                return LIVE_RED_POCKET_NOT_FOUND;
            case 27002:
                return LIVE_RED_POCKET_FINISH_OUT;
            case 27003:
                return LIVE_RED_POCKET_RECORD_FAIL;
            case 27004:
                return LIVE_RED_POCKET_RECORD_NOT_FOUND;
            case 27005:
                return LIVE_RED_POCKET_RECORD_NOT_START;
            case 27006:
                return LIVE_RED_POCKET_RECORD_END;
            case 27007:
                return LIVE_RED_POCKET_RECORD_HAVE_GET;
            default:
                return NONE;

        }
    }
}
