package com.dbms.UrbanClaps.controller;

import com.dbms.UrbanClaps.dao.ServiceProviderDao;
import com.dbms.UrbanClaps.model.ServiceProvider;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Retention;
import java.util.List;

@RestController
@RequestMapping(path = "manager")
@CrossOrigin(origins = "http://localhost:3000/")
public class ManagerController {

    @Autowired
    ServiceProviderDao serviceProviderDao;



    @PatchMapping("{id}/verified")
    public ResponseEntity<List<ServiceProvider>> updateVerifyStatus(@PathVariable Long id) {
        try{
                if(id == null)
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

                List<ServiceProvider> check = serviceProviderDao.getProviderByID(id);

                if(check == null){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }else {
                    List<ServiceProvider> obj = serviceProviderDao.updateVerifyStatus(id);
                    System.out.println(obj.toString());
                    if(obj.isEmpty())
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                    else
                        return new ResponseEntity<>(obj,HttpStatus.OK);
                }
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("editProvider")
    public ResponseEntity<String> editProviderDetails(@RequestBody ServiceProvider serviceProvider){
        try{
            serviceProviderDao.editDetails(serviceProvider);
            return new ResponseEntity<>("You edited",HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getProvider")
    public ResponseEntity<List<ServiceProvider>> getProviderById(@RequestParam Long id){

        List<ServiceProvider> provider = serviceProviderDao.getProviderByID(id);
        if(provider.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        System.out.println(provider.toString());
        return new ResponseEntity<>(provider,HttpStatus.OK);
    }



}
