package com.shibo.dao;

import com.shibo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author shibo
 */
@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    User findByToken(String token);
}
