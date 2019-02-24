package com.hendisantika.springbootjwtmysql.repository;

import com.hendisantika.springbootjwtmysql.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
    User getById(int id);

    User getByEmailAddress(String emailAddress);

    @Transactional
    void deleteById(int userId);

    @Transactional
    @Modifying
    @Query(value = "insert into token (token, userId, expirationTime) values (token, userId, now() + interval 1 DAY )", nativeQuery = true)
    void savePasswordToken(
            @Param(value = "token") String token,
            @Param(value = "userId") int userId
    );

    @Query(value = "select * from token where emailAddress=username", nativeQuery = true)
    String getTokenByUserName(@Param(value = "username") String username);
}
