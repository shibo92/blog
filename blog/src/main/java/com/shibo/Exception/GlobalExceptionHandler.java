package com.shibo.Exception;

import com.shibo.common.JsonResult;
import com.shibo.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 全局异常处理
     */
    @ExceptionHandler
    public JsonResult handleException(HttpServletRequest request, HttpServletResponse response, final Exception e) {
        log.error(e.getMessage(), e);
        return new JsonResult(ResultCode.SERVER_ERROR, e.getMessage());
        /*if (e instanceof AlertException) {//可以在前端Alert的异常
            if (((AlertException) e).getRetCode() != null) {//预定义异常
                return new JsonResult(((AlertException) e).getRetCode());
            } else {
                return new JsonResult(1, e.getMessage() != null ? e.getMessage() : "");
            }
        } else {//其它异常
            if (Util.isProduct()) {//如果是正式环境，统一提示
                return new JsonResult(ResultCode.SERVER_ERROR);
            } else {//测试环境，alert异常信息
                return new Result(1, StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : e.toString());
            }
        }*/
    }

}
