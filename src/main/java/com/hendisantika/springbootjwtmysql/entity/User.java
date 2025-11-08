package com.hendisantika.springbootjwtmysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

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
    private String name;

    @Column(name = "salary", nullable = true, length = 10)
    private Integer salary;
}