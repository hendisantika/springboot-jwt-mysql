package com.hendisantika.springbootjwtmysql.controller;

import com.hendisantika.springbootjwtmysql.entity.User;
import com.hendisantika.springbootjwtmysql.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jwt-mysql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-02-25
 * Time: 06:43
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    @NonNull
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }
}