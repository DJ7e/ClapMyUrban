package com.dbms.UrbanClaps.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

    private Integer user_id;
    private String user_fname;
    private String user_mname;
    private String user_lname;
    private String user_phone_number;
    private String user_email_id;
    private String user_password;
    private String user_address;
    private String user_photo;
    private String user_aadhar_number;
    private String user_aadhar_verification;
    private String user_account_number;
    private String user_IFSC;

    public User(String user_fname, String user_mname, String user_lname, String user_phone_number, String user_email_id, String user_password, String user_address, String user_photo, String user_aadhar_number, String user_aadhar_verification, String user_account_number, String user_IFSC) {
        this.user_fname = user_fname;
        this.user_mname = user_mname;
        this.user_lname = user_lname;
        this.user_phone_number = user_phone_number;
        this.user_email_id = user_email_id;
        this.user_password = user_password;
        this.user_address = user_address;
        this.user_photo = user_photo;
        this.user_aadhar_number = user_aadhar_number;
        this.user_aadhar_verification = user_aadhar_verification;
        this.user_account_number = user_account_number;
        this.user_IFSC = user_IFSC;
    }

}
