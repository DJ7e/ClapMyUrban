package com.dbms.UrbanClaps.dao;

import com.dbms.UrbanClaps.model.ServicesProvided;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ServicesProvidedDao {


    public String createService(ServicesProvided servicesProvided);

    List<ServicesProvided> findAllServices();

    List<ServicesProvided> findServicesByCategory(String category);

    List<ServicesProvided> findServicesByProvider(Long providerId);


}
