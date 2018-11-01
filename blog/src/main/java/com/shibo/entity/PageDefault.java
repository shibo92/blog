package com.shibo.entity;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author shibo
 */
public class PageDefault extends PageRequest {
    public PageDefault(int page) {
        // this(page, 10, new Sort(new Sort.Order(Sort.Direction.DESC, "createTime")));
        this(page, 3, Sort.Direction.DESC, "createTime");
    }

    private PageDefault(int page, int size, Sort.Direction direction, String... properties) {
        super(page, size, direction, properties);
    }

    public PageDefault(int page, int size, Sort sort) {
        super(page, size, sort);
    }
}
