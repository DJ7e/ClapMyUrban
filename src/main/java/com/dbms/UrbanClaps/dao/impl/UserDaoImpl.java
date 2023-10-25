package com.dbms.UrbanClaps.dao.impl;

import com.dbms.UrbanClaps.dao.UserDao;
import com.dbms.UrbanClaps.model.ServicesProvided;
import com.dbms.UrbanClaps.model.User;
import com.dbms.UrbanClaps.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public ResponseEntity<String> createUser(User obj) {
        try {
            jdbcTemplate.update(
                    "INSERT INTO website_user VALUE (?,?,?,?,?,?,?,?,?)",
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
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>("Na ho Pye", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public List<User> findAllUsers(){
        List<User> result  = jdbcTemplate.query(
                "SELECT * FROM website_user",
                new UserRowMapper()
        );
        return result;
    }

    @Override
    public List<User> findUsersByName(String name){
        List<User> result  = jdbcTemplate.query(
                "SELECT * FROM website_user WHERE user_fname = ?",
                new UserRowMapper(),
                name
        );
        return result;
    }

    public static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return User.builder()
                    .userId(rs.getLong("user_id"))
                    .firstName(rs.getString("user_fname"))
                    .middleName(rs.getString("user_mname"))
                    .lastName(rs.getString("user_lname"))
                    .phoneNumber(rs.getString("user_phone_number"))
                    .emailId(rs.getString("user_email_id"))
                    .password(rs.getString("user_password"))
                    .address(rs.getString("user_address"))
                    .userPhoto(rs.getString("user_photo"))
                    .build();
        }
    }


}