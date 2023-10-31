package com.dbms.UrbanClaps.dao;

import com.dbms.UrbanClaps.model.*;
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

    ServiceProvider getProviderByUsernameAKAEmailId(String username);

    Manager getManagerByUsernameAKAEmailId(String username);

    Admin getAdminByUsernameAKAEmailId(String username);
}
