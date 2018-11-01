package com.shibo.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * 博客分类
 *
 * @author shibo
 */
@Entity
@Table(name = "t_category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer pid;

    private Integer userId;

    private String content;

    @CreatedDate
    private Date createTime;

    private Integer isDel;
}