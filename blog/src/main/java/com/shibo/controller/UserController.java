package com.shibo.controller;

import com.shibo.common.JsonResult;
import com.shibo.feign.client.CommentsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
}
