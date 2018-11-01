package com.shibo.dao;

import com.shibo.entity.Blog;
import com.shibo.entity.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shibo
 */
@Repository
public interface CategoryDao extends JpaRepository<Category,Integer> {

}
