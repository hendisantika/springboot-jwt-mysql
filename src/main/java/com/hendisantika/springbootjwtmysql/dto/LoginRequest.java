package com.hendisantika.springbootjwtmysql.dto;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jwt-mysql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-02-25
 * Time: 06:52
 */
@Data
public class LoginRequest {

    private String emailAddress;

    private String password;

}