package com.hendisantika.springbootjwtmysql.repository;

import com.hendisantika.springbootjwtmysql.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jwt-mysql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-02-25
 * Time: 06:42
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
