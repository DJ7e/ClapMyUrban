package com.dbms.UrbanClaps.controller;


import com.dbms.UrbanClaps.dao.ServicesProvidedDao;
import com.dbms.UrbanClaps.model.ServicesProvided;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "service")
public class Service {

    @Autowired
    ServicesProvidedDao servicesProvidedDao;
    @PostMapping("addservice")
    public String serviceToAdd(@RequestParam ServicesProvided servicesProvided){
        return servicesProvidedDao.createService(servicesProvided);
    }

}
