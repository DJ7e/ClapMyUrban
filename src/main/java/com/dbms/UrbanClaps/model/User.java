package com.dbms.UrbanClaps.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Long userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String emailId;
    private String password;
    private String address;
    private String userPhoto;


    public User(String user_fname, String user_mname, String user_lname, String user_phone_number, String user_email_id, String user_password, String user_address, String user_photo) {
        this.firstName = user_fname;
        this.middleName = user_mname;
        this.lastName = user_lname;
        this.phoneNumber = user_phone_number;
        this.emailId = user_email_id;
        this.password = user_password;
        this.address = user_address;
        this.userPhoto = user_photo;

    }

}
