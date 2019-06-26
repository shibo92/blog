package com.shibo.dao;

import com.shibo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author shibo
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByToken(String token);

    User findByUsernameAndPassword(String username, String password);
}
