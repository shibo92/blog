package com.shibo.config;

import com.shibo.common.JsonResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局异常返回
 *
 * @author shibo
 */
@EnableWebMvc
@Configuration
public class GlobalResultConfig {
    @RestControllerAdvice
    static class ResultResponseAdvice implements ResponseBodyAdvice<Object> {
        @Override
        public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
            /*//获取当前处理请求的controller的方法
            String methodName=returnType.getMethod().getName();
            // 不拦截/不需要处理返回值 的方法
            String method= "login"; //如登录
            // false不拦截(不执行beforeBodyWrite方法), true拦截
            return !method.equals(methodName);*/
            return true;
        }

        @Override
        public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
            if (body instanceof JsonResult) {
                return body;
            }
            return new JsonResult(body);
        }
    }
}
