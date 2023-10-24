package com.dbms.UrbanClaps.controller;


import com.dbms.UrbanClaps.dao.ServiceProviderDao;
import com.dbms.UrbanClaps.dao.ServicesProvidedDao;
import com.dbms.UrbanClaps.model.ServicesProvided;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "service")
public class ServiceController {

    @Autowired
    ServicesProvidedDao servicesProvidedDao;

    @Autowired
    ServiceProviderDao serviceProviderDao;


    //Working
    @PostMapping("addservice")
    public String serviceToAdd(@RequestBody ServicesProvided ser){
        ServicesProvided obj = ServicesProvided.builder()
                .id(ser.getId())
                .name(ser.getName())
                .category(ser.getCategory())
                .description(ser.getDescription())
                .price(ser.getPrice())
                .build();
        System.out.println(obj.toString());
        return servicesProvidedDao.createService(obj);
    }

    @PostMapping("changename")
    public String changeName(@RequestParam Long id,String name){
        return servicesProvidedDao.changeName(id,name);
    }

    //Working
    @GetMapping("getAllServ")
    public ResponseEntity<List<ServicesProvided>> getServicesByCategory(@RequestParam(required = false) String category){
        try {
            List<ServicesProvided> services = new ArrayList<ServicesProvided>();

            if (category == null)
                services.addAll(servicesProvidedDao.findAllServices());
            else
                services.addAll(servicesProvidedDao.findServicesByCategory(category));
            if (services.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(services, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Working
    @GetMapping("getNameServ")
    public ResponseEntity<List<ServicesProvided>> getServicesByName(@RequestParam(required = false) Long name){
        try {
            List<ServicesProvided> services = new ArrayList<ServicesProvided>();

            if (name == null)
                services.addAll(servicesProvidedDao.findAllServices());
            else
                services.addAll(serviceProviderDao.findServicesOfferedByProvider(name));
            if (services.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(services, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
