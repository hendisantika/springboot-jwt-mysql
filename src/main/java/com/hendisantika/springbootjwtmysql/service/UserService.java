package com.hendisantika.springbootjwtmysql.service;

import com.hendisantika.springbootjwtmysql.entity.User;
import com.hendisantika.springbootjwtmysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jwt-mysql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-02-25
 * Time: 06:42
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}