package com.dbms.UrbanClaps.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceProvider {

        private Long id;
        private String firstName;
        private String middleName;
        private String lastName;
        private String phoneNo;
        private String emailId;
        private String password;
        private String address;
        private String description;
        private String photo;
        private String aadharNumber;
        private String aadharVerification;
        private String accountNo;
        private String IFSC;
        private Long manager;
        private Long service;



}
