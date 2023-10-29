package com.dbms.UrbanClaps.controller;


import com.dbms.UrbanClaps.config.AuthenticationService;
import com.dbms.UrbanClaps.dao.ServiceProviderDao;
import com.dbms.UrbanClaps.dao.ServicesProvidedDao;
import com.dbms.UrbanClaps.model.ServicesProvided;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "service")
@CrossOrigin(origins = "http://localhost:3000/")
public class ServiceController {

    @Autowired
    ServicesProvidedDao servicesProvidedDao;

    @Autowired
    ServiceProviderDao serviceProviderDao;

    @Autowired
    AuthenticationService authenticationService;


    //Working
    @PostMapping("addservice")
    public String serviceToAdd(@RequestBody ServicesProvided ser){

        //        ADMIN Sign IN REQD

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

//        KOI BHI
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
    public ResponseEntity<List<ServicesProvided>> getServicesByName(@RequestParam(required = false) Long name, HttpSession session) {

        if (authenticationService.isAuthenticated(session) && (authenticationService.getCurrentRole(session).equals("4")||authenticationService.getCurrentRole(session).equals("3"))) {

            //        MANAGER AND ADMIN Sign IN REQD
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
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"You Are Not Authorized");
        }
    }
}
