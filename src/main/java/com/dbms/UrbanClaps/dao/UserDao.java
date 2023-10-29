package com.dbms.UrbanClaps.dao;

import com.dbms.UrbanClaps.model.LoginUser;
import com.dbms.UrbanClaps.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserDao {

    ResponseEntity<String> createUser(User obj);

    List<User> findAllUsers();

    List<User> findUsersByName(String name);

    User getUserByUsernameAKAEmailId(String username);

    List<LoginUser> getWUCredentials(LoginUser loginUser);

    List<LoginUser> getAdminCredentials(LoginUser loginUser);
}
