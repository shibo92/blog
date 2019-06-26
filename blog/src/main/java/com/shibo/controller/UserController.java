package com.shibo.controller;

import com.shibo.common.Constants;
import com.shibo.common.JsonResult;
import com.shibo.common.ResultCode;
import com.shibo.entity.User;
import com.shibo.security.CurrentUser;
import com.shibo.service.UserService;
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
    private UserService userService;

    @PostMapping(value = "/login")
    @ResponseBody
    public JsonResult login(@RequestParam(value = "username") String username,
                            @RequestParam(value = "password") String password) {
        User user = userService.findByUsernameAndPassword(username, password);
        if (null == user) {
            return new JsonResult(ResultCode.AUTH_ERROR, "用户名或密码错误");
        }
        return JsonResult.getSuccessfulResult(user);
    }


    @GetMapping("/info")
    @ResponseBody
    public JsonResult info(@CurrentUser User user) {
        if (null == user) {
            return new JsonResult(ResultCode.AUTH_ERROR, "authentication error");
        }
        return JsonResult.getSuccessfulResult(user);
    }
}
