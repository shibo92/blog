package com.shibo.controller;

import com.shibo.common.JsonResult;
import com.shibo.entity.Blog;
import com.shibo.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author shibo
 */
@Controller
@Slf4j
public class HomeController {
    @GetMapping(value = {"/", "index", "home"})
    public String get() {
        return "redirect:blogs";
    }
}