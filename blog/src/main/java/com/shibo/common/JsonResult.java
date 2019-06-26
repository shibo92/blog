package com.shibo.common;

/**
 * @author shibo
 */
public class JsonResult {
    private int code;
    private String message;
    private Object data;

    public JsonResult() {
    }

    public JsonResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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

    public static JsonResult getSuccessfulResult() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setInfo(ResultCode.SUCCESS, "success");
        return jsonResult;
    }

    public static JsonResult getSuccessfulResult(Object data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setInfo(ResultCode.SUCCESS, "success", data);
        return jsonResult;
    }


    public void setInfo(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public void setInfo(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
