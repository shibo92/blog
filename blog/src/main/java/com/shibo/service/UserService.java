package com.shibo.service;

import com.shibo.dao.UserDao;
import com.shibo.entity.Blog;
import com.shibo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author shibo
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void save(User user) {
        userDao.save(user);
        userDao.flush();
    }

    public User findById(Integer id) {
        return userDao.findById(id).orElse(new User());
    }

    public User findByToken(String token) {
        return userDao.findByToken(token);
    }

    public User findByUsernameAndPassword(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, password);
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        userDao.save(user);
        return user;
    }
}
