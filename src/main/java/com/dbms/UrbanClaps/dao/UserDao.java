package com.dbms.UrbanClaps.dao;

import com.dbms.UrbanClaps.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    ResponseEntity<String> createUser(User obj);

}
