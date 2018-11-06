package com.shibo.common;

/**
 * @author shibo
 */
public class JsonResult {
    private String code;
    private String message;
    private Object data;

    public JsonResult() {
    }

    public JsonResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonResult(String code, String message, Object data) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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


    public void setInfo(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public void setInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
