package com.shibo.dao;

import com.shibo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shibo
 */
@Repository
public interface UserDao extends JpaRepository<User,Integer> {
}
