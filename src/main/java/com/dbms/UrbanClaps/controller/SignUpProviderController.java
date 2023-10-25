package com.dbms.UrbanClaps.controller;

import com.dbms.UrbanClaps.dao.ServiceProviderDao;
import com.dbms.UrbanClaps.model.ServiceProvider;
import com.dbms.UrbanClaps.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "signup/provider")
public class SignUpProviderController {

    @Autowired
    ServiceProviderDao serviceProviderDao;

    @GetMapping()
    public String signupProviderPage(){
        return "Ye faltu hai";
    }

    @PostMapping()
    public ResponseEntity<String> addProvider(@RequestBody ServiceProvider sp){
        ServiceProvider obj = ServiceProvider.builder()
                .id(sp.getId())
                .firstName(sp.getFirstName())
                .middleName(sp.getMiddleName())
                .lastName(sp.getLastName())
                .phoneNo(sp.getPhoneNo())
                .emailId(sp.getEmailId())
                .password(sp.getPassword())
                .address(sp.getAddress())
                .description(sp.getDescription())
                .photo(sp.getPhoto())
                .aadharNumber(sp.getAadharNumber())
                .aadharVerification(sp.getAadharVerification())
                .accountNo(sp.getAccountNo())
                .IFSC(sp.getIFSC())
                .manager(sp.getManager())
                .category(sp.getCategory())
                .build();
        System.out.println(obj.toString());
        return serviceProviderDao.createProvider(obj);
    }

}
