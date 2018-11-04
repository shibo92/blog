package com.shibo.feign.fallback;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by lin on 2017/6/29.
 */
@Slf4j
@Component
public class BaseFallBack<T> implements FallbackFactory<T> {

    @Override
    public T create(Throwable cause) {
        return null;
    }

    /**
     * 触发hystrix的fallback时打印error日志
     * @param cause
     */
    void fallbackTriggered(Throwable cause) {
        log.error("请求数据服务返回状态异常！", cause);
    }
}
