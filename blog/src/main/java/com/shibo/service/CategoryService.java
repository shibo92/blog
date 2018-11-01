package com.shibo.service;

import com.shibo.dao.BlogDao;
import com.shibo.dao.CategoryDao;
import com.shibo.entity.Blog;
import com.shibo.entity.Category;
import com.shibo.entity.PageDefault;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shibo
 */
@Service
@Slf4j
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public void save(Category category) {
        categoryDao.save(category);
    }

    public Category findById(Integer id) {
        return categoryDao.findById(id).orElse(new Category());
    }

    public List<Category> list() {
        return categoryDao.findAll();
    }
}
