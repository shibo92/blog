package com.shibo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author shibo
 */
@Entity
@Table(name = "t_user_info")
@Data
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private String name;

    private String sex;

    private String birthday;

    private String email;

    private String phone;

    private Integer isDel;

}