package com.shibo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author shibo
 */
@Controller
@Slf4j
@RequestMapping("sentry")
public class SentryController {

    @RequestMapping("/error")
    public void test(){
        log.error("test error..........");
    }
}
