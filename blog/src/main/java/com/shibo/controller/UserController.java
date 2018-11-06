package com.shibo.controller;

import com.shibo.common.JsonResult;
import com.shibo.entity.User;
import com.shibo.feign.client.CommentsClient;
import com.shibo.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author shibo
 */

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private CommentsClient commentsClient;

    @GetMapping("/test")
    @ResponseBody
    public JsonResult get() {
        return commentsClient.get(100);
    }

    @GetMapping("/current")
    @ResponseBody
    public JsonResult testCurrentUser(@CurrentUser User user) {
        System.out.println(user);
        return JsonResult.getSuccessfulResult(user);
    }
}
