package com.shibo.service;

import com.shibo.dao.BlogDao;
import com.shibo.entity.Blog;
import com.shibo.entity.PageDefault;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author shibo
 */
@Service
@Slf4j
public class BlogService {

    @Autowired
    private BlogDao blogDao;

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Blog blog) {
        blogDao.save(blog);
    }

    public Blog findById(Integer id) {
        return blogDao.findById(id).orElse(new Blog());
    }

    public Page<Blog> list(String keyword, Integer page) {
        page = null == page ? 0 : page;
        return blogDao.findAll(this.getListSpecification(null, keyword), new PageDefault(page));
    }

    public Page<Blog> listByCategory(Integer categoryId, String keyword, Integer page) {
        page = null == page ? 0 : page;
        return blogDao.findAll(this.getListSpecification(categoryId, keyword), new PageDefault(page));
    }

    private Specification<Blog> getListSpecification(Integer categoryId, String keyword) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();
            if (null != categoryId) {
                Path<Integer> blogCategory = root.get("categoryId");
                predicates.add(criteriaBuilder.equal(blogCategory, categoryId));
            }
            if (StringUtils.isNotBlank(keyword)) {
                Path<String> blogTitle = root.get("title");
                Path<String> blogContent = root.get("plainContent");
                final String keywordParam = "%" + keyword + "%";
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(blogTitle, keywordParam),
                        criteriaBuilder.like(blogContent, keywordParam)
                ));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };
    }

    public List findByLabels(Blog blog) {
        if (StringUtils.isBlank(blog.getLabels())) {
            return null;
        }
        String[] labels = blog.getLabels().split(",");
        StringBuilder sql = new StringBuilder("SELECT blog.id, blog.title from t_blog blog where blog.is_del = 0 ");
        for (int i = 0; i < labels.length; i++) {
            if(i == 0){
                sql.append("AND");
            } else {
                sql.append("OR");
            }
            sql.append(" FIND_IN_SET('").append(labels[i]).append("', blog.labels) ");
        }
        sql.append("ORDER BY create_time DESC limit 0,10 ");
        return entityManager.createNativeQuery(sql.toString()).getResultList();
    }
}
