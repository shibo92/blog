package com.shibo.dao;

import com.shibo.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shibo
 */
@Repository
public interface BlogDao extends JpaRepository<Blog,Integer>, JpaSpecificationExecutor<Blog> {
    /**
     * 根据blog title模糊查找
     * @param keyword
     * @param pageable
     * @return
     */
    Page<Blog> findByTitleLike(String keyword, Pageable pageable);

    /**
     * 根据分类和blog title模糊查找
     * @param categoryId
     * @param keyword
     * @param pageable
     * @return
     */
    Page<Blog> findByCategoryIdAndTitleLike(Integer categoryId, String keyword, Pageable pageable);

}
