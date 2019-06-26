package com.shibo.controller;

import com.shibo.common.JsonResult;
import com.shibo.feign.client.CommentsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shibo
 */

@Controller
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentsClient commentsClient;

    @GetMapping("/test")
    @ResponseBody
    public JsonResult get() {
        return commentsClient.get(100);
    }


}
