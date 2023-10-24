package com.dbms.UrbanClaps.dao.impl;

import com.dbms.UrbanClaps.dao.UserDao;
import com.dbms.UrbanClaps.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public ResponseEntity<String> createUser(User obj) {
        jdbcTemplate.update(
                "INSERT INTO website_user VALUES (?,?,?,?,?,?,?,?,?)",
                obj.getUserId(),
                obj.getFirstName(),
                obj.getMiddleName(),
                obj.getLastName(),
                obj.getPhoneNumber(),
                obj.getEmailId(),
                obj.getPassword(),
                obj.getAddress(),
                obj.getUserPhoto()
        );
        return new ResponseEntity<>("AAgaye aap andar", HttpStatus.CREATED);
    }
}
