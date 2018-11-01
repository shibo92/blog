package com.shibo.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * 回复
 *
 * @author shibo
 */
@Entity
@Table(name = "t_response")
@Data
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer pid;

    private Integer userId;

    private Integer blogId;

    @CreatedDate
    private Date createTime;

    private Integer isDel;

    private String content;

}