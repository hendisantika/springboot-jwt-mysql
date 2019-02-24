package com.hendisantika.springbootjwtmysql.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jwt-mysql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-02-25
 * Time: 06:41
 */
@Data
@Entity
@Table(name = "Users")
public class User {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = true, length = 255)
    @ApiModelProperty(required = true)
    private String name;

    @Column(name = "salary", nullable = true, length = 10)
    @ApiModelProperty(required = true)
    private Integer salary;

    @Column(name = "emailAddress", nullable = false, length = 255)
    @ApiModelProperty(required = true)
    private String emailAddress;

    @Transient
    private String password;

    @Column(name = "passwordHash", nullable = false, length = 255)
    private String passwordHash;
}