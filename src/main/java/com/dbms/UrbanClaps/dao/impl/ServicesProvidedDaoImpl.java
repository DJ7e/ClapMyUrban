package com.dbms.UrbanClaps.dao.impl;

import com.dbms.UrbanClaps.dao.ServicesProvidedDao;
import com.dbms.UrbanClaps.model.ServicesProvided;
import org.springframework.stereotype.Component;

@Component
public class ServicesProvidedDaoImpl implements ServicesProvidedDao {


    @Override
    public String createService(ServicesProvided servicesProvided){
        return "Hello";
    }

}
