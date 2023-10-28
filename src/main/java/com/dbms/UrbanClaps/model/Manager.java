package com.dbms.UrbanClaps.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Manager {
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
}
