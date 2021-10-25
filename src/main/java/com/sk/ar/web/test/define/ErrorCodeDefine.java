package com.sk.ar.web.test.define;

public enum ErrorCodeDefine {

    CUSTOM_ERROR_WEB_EVENT_BASE_NULL(800, "WEB_EVENT_BASE 없음"),
    CUSTOM_ERROR_EVENT_ID_NULL(801, "EVENT_ID 없음"),
    CUSTOM_ERROR_AR_EVENT_INFO_NULL(802, "AR_EVENT 정보 없음")
    ;

    int code;
    String msg;

    ErrorCodeDefine(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }

    public static String getEventErrorMessage(int code) {
        for (ErrorCodeDefine errCode : ErrorCodeDefine.values()) {
            if (code == errCode.code) {
                return errCode.msg();
            }
        }
        return null;
    }

    public static String getLogErrorMessage(int code) {
        for (ErrorCodeDefine errCode : ErrorCodeDefine.values()) {
            if (code == errCode.code) {
                return "error {} " + errCode.msg();
            }
        }
        return null;
    }
}
