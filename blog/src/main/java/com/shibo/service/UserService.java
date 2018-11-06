package com.shibo.service;

import com.shibo.dao.UserDao;
import com.shibo.entity.Blog;
import com.shibo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shibo
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void save(User user){
        userDao.save(user);
        userDao.flush();
    }

    public User findById(Integer id){
        return userDao.findById(id).orElse(new User());
    }

    public User findByToken(String token){
        return userDao.findByToken(token);
    }
}
