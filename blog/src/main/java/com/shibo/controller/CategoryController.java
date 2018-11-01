package com.shibo.controller;

import com.shibo.common.JsonResult;
import com.shibo.entity.Blog;
import com.shibo.entity.Category;
import com.shibo.service.BlogService;
import com.shibo.service.CategoryService;
import javassist.compiler.ast.Keyword;
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
@RequestMapping("categories")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/{categoryId}")
    public String get(@PathVariable(value = "categoryId") Integer categoryId, Model model) {
        model.addAttribute("category", categoryService.findById(categoryId));
        return "category/view";
    }

    @PostMapping("/{categoryId}")
    public JsonResult save(@PathVariable(value = "categoryId") Category category, Model model) {
        categoryService.save(category);
        return JsonResult.getSuccessfulResult();
    }

    @PutMapping("/{categoryId}")
    public JsonResult update(@PathVariable(value = "categoryId") Category category, Model model) {
        categoryService.save(category);
        return JsonResult.getSuccessfulResult();
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("categories", categoryService.list());
        return "category/list";
    }

    /*@GetMapping("/{categoryId}/blogs")
    public String listBlog(@PathVariable("categoryId") Integer categoryId,
                           @RequestParam(value = "keyword", required = false) String keyword,
                           @RequestParam(value = "page", required = false) Integer page,
                           Map<String,Object> map) {
        Page<Blog> blogs = blogService.listByCategory(categoryId, keyword, page);
        map.put("blogs", blogs);
        map.put("keyword", keyword);
        return "blog/list";
    }*/

    @GetMapping("/{categoryId}/blogs")
    @ResponseBody
    public JsonResult listBlog(@PathVariable("categoryId") Integer categoryId,
                           @RequestParam(value = "keyword", required = false) String keyword,
                           @RequestParam(value = "page", required = false) Integer page) {
        Page<Blog> blogs = blogService.listByCategory(categoryId, keyword, page);
        Map<String,Object> map = new HashMap<>(2);
        map.put("blogs", blogs);
        map.put("keyword", keyword);
        return JsonResult.getSuccessfulResult(map);
    }
}
