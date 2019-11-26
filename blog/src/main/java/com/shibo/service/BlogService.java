package com.shibo.service;

import com.shibo.Exception.BlogNotFoundException;
import com.shibo.common.Constants;
import com.shibo.dao.BlogDao;
import com.shibo.entity.Blog;
import com.shibo.entity.PageDefault;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedisTemplate redisTemplate;

    public void save(Blog blog) {
        blogDao.save(blog);
    }

    public Blog findById(Integer id) throws Exception {
        final String blogKey = Constants.REDIS_KEY_BLOG + ":" + id;
        Blog blog = (Blog) redisTemplate.opsForValue().get(blogKey);
        if (null == blog) {
            blog = blogDao.findById(id).orElse(null);
            if(null == blog){
                throw new BlogNotFoundException(id);
            }
            redisTemplate.opsForValue().set(blogKey, blog, Constants.REDIS_EXPIRE_30_SECOND, TimeUnit.SECONDS);
        }
        return blog;
    }

    public Page<Blog> list(String keyword, Integer page) {
        page = null == page ? 0 : page;
        return blogDao.findAll(this.getListSpecification(null, keyword), new PageDefault(page));
    }
    public List<Blog> list2(String keyword, Integer page) {
        return new ArrayList<>();
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
            if (i == 0) {
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
