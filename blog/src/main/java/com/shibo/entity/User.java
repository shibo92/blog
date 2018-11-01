package com.shibo.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author shibo
 */
@Entity
@Table(name = "t_user")
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String username;

    private String password;

    private String question;

    private String answer;

    @CreatedDate
    private Date regTime;

    private Integer isDel;

}