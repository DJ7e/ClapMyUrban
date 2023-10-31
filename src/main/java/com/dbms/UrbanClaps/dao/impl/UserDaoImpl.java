package com.dbms.UrbanClaps.dao.impl;

import com.dbms.UrbanClaps.dao.UserDao;
import com.dbms.UrbanClaps.model.*;
import com.dbms.UrbanClaps.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    ServicesProviderDaoImpl servicesProviderDao;

    @Override
    public ResponseEntity<String> createUser(User obj) {
        try {
            jdbcTemplate.update(
                    "INSERT INTO website_user(user_fname, user_mname, user_lname, user_phone_number, user_email_id, user_password, user_address, user_photo) VALUE (?,?,?,?,?,?,?,?)",
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

    @Override
    public User getUserByUsernameAKAEmailId(String username) {
        List<User> result  = jdbcTemplate.query(
                "SELECT * FROM website_user WHERE user_email_id = ?",
                new UserRowMapper(),
                username
        );
        System.out.println("hola" + result.toString());
        return result.get(0);

    }
    @Override
    public ServiceProvider getProviderByUsernameAKAEmailId(String username) {
        List<ServiceProvider> result  = jdbcTemplate.query(
                "SELECT * FROM service_provider WHERE provider_email_id = ?",
                new ServicesProviderDaoImpl.ServiceProviderRowMapper(),
                username
        );
        System.out.println("hola" + result.toString());
        return result.get(0);

    }

    @Autowired
    ManagerDaoImpl managerDao;

    @Override
    public Manager getManagerByUsernameAKAEmailId(String username) {
        List<Manager> result  = jdbcTemplate.query(
                "SELECT * FROM manager WHERE manager_email_id = ?",
                new ManagerDaoImpl.ManagerRowMapper(),
                username
        );
        System.out.println("hola" + result.toString());
        return result.get(0);

    }



    @Override
    public Admin getAdminByUsernameAKAEmailId(String username) {
        List<Admin> result  = jdbcTemplate.query(
                "SELECT * FROM manager WHERE manager_email_id = ?",
                new AdminRowMapper(),
                username
        );
        System.out.println("hola" + result.toString());
        return result.get(0);
    }


    public static class AdminRowMapper implements RowMapper<Admin> {
        @Override
        public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Admin.builder()
                    .id(rs.getLong("admin_id"))
                    .firstName(rs.getString("admin_fname"))
                    .middleName(rs.getString("admin_mname"))
                    .lastName(rs.getString("admin_lname"))
                    .phoneNo(rs.getString("admin_phone_number"))
                    .emailId(rs.getString("admin_email_id"))
                    .password(rs.getString("admin_password"))
                    .address(rs.getString("admin_address"))
                    .photo(rs.getString("admin_photo"))
                    .build();
        }
    }
    @Override
    public List<LoginUser> getWUCredentials(LoginUser loginUser) {
        String emailId = loginUser.getUsername();
        List<LoginUser> result = jdbcTemplate.query("select user_email_id as username,user_password as password from website_user \n" +
                        "where user_email_id = ?",
                new LoginWURowMapper(),
                emailId
        );
        return result;
    }

    @Override
    public List<LoginUser> getAdminCredentials(LoginUser loginUser) {
        String emailId = loginUser.getUsername();
        List<LoginUser> result = jdbcTemplate.query("select admin_email_id as username,admin_password as password from website_admin \n" +
                        "where admin_email_id = ?",
                new LoginWURowMapper(),
                emailId
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



    public static class LoginWURowMapper implements RowMapper<LoginUser> {
        @Override
        public LoginUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            return LoginUser.builder()
                    .username(rs.getString("username"))
                    .password(rs.getString("password"))
                    .role(null)
                    .build();
        }
    }

}
