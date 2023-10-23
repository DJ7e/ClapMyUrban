package com.dbms.UrbanClaps.dao;

import com.dbms.UrbanClaps.model.ServicesProvided;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesProvidedDao {


    public String createService(ServicesProvided servicesProvided);

}
