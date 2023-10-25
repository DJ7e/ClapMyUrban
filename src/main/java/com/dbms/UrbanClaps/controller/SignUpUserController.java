package com.dbms.UrbanClaps.controller;


import com.dbms.UrbanClaps.dao.UserDao;
import com.dbms.UrbanClaps.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "signup")
public class SignUpUserController {

    @Autowired
    UserDao userDao;

    @GetMapping()
    public String signupUserPage(){
        return "Ye faltu hai";
    }

    @PostMapping("users")
    public ResponseEntity<String> addUser(@RequestBody User user){
        User obj = User.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .emailId(user.getEmailId())
                .password(user.getPassword())
                .address(user.getAddress())
                .userPhoto(user.getUserPhoto())
                .build();
        System.out.println(obj.toString());
        return userDao.createUser(obj);
    }

}
