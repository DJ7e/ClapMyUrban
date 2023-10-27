package com.dbms.UrbanClaps.controller;

import com.dbms.UrbanClaps.dao.UserDao;
import com.dbms.UrbanClaps.model.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "user")
public class UserController {

    @Autowired
    UserDao userDao;

    @PostMapping("createSlot")
    public ResponseEntity<String> createSlot(@RequestParam String time,@RequestParam Long serviceid){





        return null;

    }

}
