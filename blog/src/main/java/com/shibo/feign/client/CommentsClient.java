package com.shibo.feign.client;

import com.shibo.common.JsonResult;
import com.shibo.feign.fallback.CommentsClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author shibo
 * comment参数为服务提供者的application name
 */
@FeignClient(value = "comment",fallback = CommentsClientFallback.class)
public interface CommentsClient {
    @RequestMapping(method = RequestMethod.GET, value = "/comments/{commentId}")
    JsonResult get(@PathVariable("commentId") Integer commentId);
}
