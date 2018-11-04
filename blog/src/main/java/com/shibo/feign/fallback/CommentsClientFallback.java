package com.shibo.feign.fallback;

import com.shibo.common.JsonResult;
import com.shibo.feign.client.CommentsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author shibo
 */
@Slf4j
@Component
public class CommentsClientFallback implements CommentsClient {
    @Override
    public JsonResult get(Integer commentId) {
        log.error("CommentsClient获取数据异常");
        return JsonResult.getSuccessfulResult();
    }
}
