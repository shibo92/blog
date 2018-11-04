package com.shibo.feign.fallback;

import com.shibo.common.JsonResult;
import com.shibo.feign.client.CommentsClient;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CommentsClientFallback extends BaseFallBack<CommentsClient> {

    @Override
    public CommentsClient create(Throwable cause){
        return new CommentsClient() {
            @Override
            public JsonResult get(Integer commentId) {
                fallbackTriggered(cause);
                return JsonResult.getSuccessfulResult();
            }
        };
    }
}
