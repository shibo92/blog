package com.shibo.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author shibo
 */
@Entity
@Table(name = "t_blog")
@Data
public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer categoryId;

    private String type;

    private String loadUrl;

    private String author;

    private String title;

    private String labels;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date alterTime;

    private String state;

    private Integer isDel;

    @Column(columnDefinition="TEXT")
    private String content;

    private String sourceContent;

    private String plainContent;
}