package com.dbms.UrbanClaps.controller;


import com.dbms.UrbanClaps.dao.ServicesProvidedDao;
import com.dbms.UrbanClaps.model.ServicesProvided;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "service")
public class Service {

    @Autowired
    ServicesProvidedDao servicesProvidedDao;
    @PostMapping("addservice")
    public String serviceToAdd(@RequestBody ServicesProvided ser){
        ServicesProvided obj = ServicesProvided.builder()
                .id(ser.getId())
                .serviceName(ser.getServiceName())
                .serviceCategory(ser.getServiceCategory())
                .serviceDes(ser.getServiceDes())
                .servicePrice(ser.getServicePrice())
                .build();
        System.out.println(obj.toString());
        return servicesProvidedDao.createService(obj);
    }

}
