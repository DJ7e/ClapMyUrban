package com.dbms.UrbanClaps.controller;

import com.dbms.UrbanClaps.dao.ManagerDao;
import com.dbms.UrbanClaps.dao.ServiceProviderDao;
import com.dbms.UrbanClaps.dao.UserDao;
import com.dbms.UrbanClaps.model.Manager;
import com.dbms.UrbanClaps.model.ServiceProvider;
import com.dbms.UrbanClaps.model.ServicesProvided;
import com.dbms.UrbanClaps.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "admin")
public class AdminController {

    @Autowired
    UserDao userDao;

    @Autowired
    ServiceProviderDao serviceProviderDao;

    @Autowired
    ManagerDao managerDao;

    @GetMapping("allUsers")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name){
        try {
            List<User> providers = new ArrayList<User>();

            if (name == null)
                providers.addAll(userDao.findAllUsers());
            else
                providers.addAll(userDao.findUsersByName(name));
            if (providers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(providers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("allProviders")
    public ResponseEntity<List<ServiceProvider>> getAllProviders(){
        try{
            List<ServiceProvider> providers = new ArrayList<ServiceProvider>();
            providers.addAll(serviceProviderDao.getAllProviders());

            if (providers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(providers,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("allManager")
    public ResponseEntity<List<Manager>> getAllManager(){
        try{
            List<Manager> managers = new ArrayList<Manager>();
            managers.addAll(managerDao.getAllManager());

            if (managers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(managers,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
