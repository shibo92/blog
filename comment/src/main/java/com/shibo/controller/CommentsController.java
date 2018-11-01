package com.shibo.controller;

import com.shibo.common.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shibo
 */
@RestController
@RequestMapping("comments")
public class CommentsController {

    @Value("${hehe.haha}")
    private String configContent;

    @GetMapping(value = "/{commentId}")
    public JsonResult get(@PathVariable(value = "commentId") Integer commentId) {
        return JsonResult.getSuccessfulResult("get a comments:" + commentId + " and config content = " + configContent);
    }
}
