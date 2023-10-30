package com.dbms.UrbanClaps.controller;


import com.dbms.UrbanClaps.config.SecurityConfig;
import com.dbms.UrbanClaps.dao.UserDao;
import com.dbms.UrbanClaps.model.LoginUser;
import com.dbms.UrbanClaps.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "signup")
@CrossOrigin(origins = "http://localhost:3000/")
public class SignUpUserController {

    @Autowired
    UserDao userDao;
    @Autowired
    SecurityConfig securityConfig;

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
                .password(securityConfig.passwordEncoder().encode(user.getPassword()))
                .address(user.getAddress())
                .userPhoto(user.getUserPhoto())
                .build();
        System.out.println(obj.toString());
        if(!userDao.getWUCredentials(LoginUser.builder()
                .username(obj.getEmailId())
                .password(obj.getPassword())
                .role(null)
                .build()
        ).isEmpty()){
            return new ResponseEntity<>("THIS EMAIL ID IS ALREADY IN USE", HttpStatus.BAD_REQUEST);
        }
        return userDao.createUser(obj);
    }

}
