package com.shibo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 博客信息
 *
 * @author shibo
 */
@Entity
@Table(name = "t_blog_statistics")
@Data
public class BlogStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer blogId;

    private String readCount;

    private String thumbUpCount;

    private Integer isDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}