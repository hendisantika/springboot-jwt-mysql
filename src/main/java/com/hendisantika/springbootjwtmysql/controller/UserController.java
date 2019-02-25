package com.hendisantika.springbootjwtmysql.controller;

import com.hendisantika.springbootjwtmysql.dto.LoginRequest;
import com.hendisantika.springbootjwtmysql.entity.User;
import com.hendisantika.springbootjwtmysql.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @PostMapping("/authenticate")
    public ResponseEntity<Map> authenticate(@RequestBody LoginRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();

        User existingUser;
        try {
            existingUser = userService.getUserByUsername(request.getEmailAddress());
            if (existingUser == null || !existingUser.getPasswordHash().equals(hash(request.getPassword()))) {
                throw new RuntimeException("Incorrect username or password");
            }
        } catch (Exception e) {
            throw new RuntimeException("Incorrect username or password");
        }
        String token = Jwts.builder()
                .setSubject(request.getEmailAddress())
                .setExpiration(new Date(System.currentTimeMillis() + 864_000_000))
                .signWith(SignatureAlgorithm.HS512, "SuperSecretStretchSecret".getBytes())
                .compact();
        response.put("token", token);
        response.put("user", existingUser);
        UUID id = UUID.randomUUID();
        userService.savePasswordToken(id.toString(), token, existingUser.getId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/username/{username}")
    public User getByUserName(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/id/{id}")
    public User getById(@PathVariable int id) {
        return userService.getById(id);
    }

    @PostMapping
    public User saveOrUpdate(@RequestBody User user) {
        boolean isNewUser = user.getId() == null;

        if (isNewUser && user.getEmailAddress() != null && user.getPassword() != null) {
            user.setPasswordHash(hash(user.getPassword()));
            User savedUser = userService.saveOrUpdate(user);
            return savedUser;
        } else {
            return userService.saveOrUpdate(user);
        }

    }

    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable int id) {
        userService.deleteById(id);
    }

    private String hash(String password) {
        String hash = "";
        if (null == password)
            return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes(), 0, password.length());
            hash = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }

    private String getTokenByUserName(String username) {
        return userService.getTokenByUserName(username);
    }


}