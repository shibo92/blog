package com.shibo.controller;

import com.shibo.common.JsonResult;
import com.shibo.entity.Blog;
import com.shibo.service.BlogService;
import com.shibo.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shibo
 */
@Controller
@RequestMapping("blogs")
@Slf4j
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{blogId}")
    public String get(@PathVariable(value = "blogId") Integer blogId, Map<String, Object> map) throws Exception {
        Blog blog = blogService.findById(blogId);
        map.put("blog", blog);
        map.put("relates", blogService.findByLabels(blog));
        return "blog/view";
    }

    @GetMapping("/{blogId}/edit")
    public String toUpdate(@PathVariable(value = "blogId") Integer blogId, Map<String, Object> map) throws Exception {
        map.put("blog", blogService.findById(blogId));
        map.put("categories", categoryService.list());
        return "blog/edit";
    }
    @GetMapping("/write")
    public String toSave(Map<String, Object> map) {
        map.put("categories", categoryService.list());
        return "blog/edit";
    }

    @PostMapping("/")
    @ResponseBody
    public JsonResult save(Blog blog) {
        blogService.save(blog);
        return JsonResult.getSuccessfulResult();
    }

    @GetMapping(value = {"","/"})
    public String list(@RequestParam(value = "keyword", required = false) String keyword,
                       @RequestParam(value = "page", required = false) Integer page,
                       Map<String, Object> map) {
        Page<Blog> blogs = blogService.list(keyword, page);
        map.put("blogs", blogs);
        map.put("keyword", keyword);
        return "blog/list";
    }

    @GetMapping(value = {"/doList"})
    @ResponseBody
    public Page<Blog> doList(@RequestParam(value = "keyword", required = false) String keyword,
                           @RequestParam(value = "page", required = false) Integer page) {
        Page<Blog> blogs = blogService.list(keyword, page);
        return blogs;
    }

    @GetMapping("/{blogId}/test")
    @ResponseBody
    public Blog getTest(@PathVariable(value = "blogId") Integer blogId) throws Exception {
        Blog blog = blogService.findById(blogId);
        return blog;
    }
}