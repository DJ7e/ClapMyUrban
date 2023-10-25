package com.dbms.UrbanClaps.dao;

import com.dbms.UrbanClaps.model.ServiceProvider;
import com.dbms.UrbanClaps.model.ServicesProvided;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ServiceProviderDao {

    List<ServicesProvided> findServicesOfferedByProvider(Long providerId);

    List<ServiceProvider> getAllProviders();

    ResponseEntity<String> createProvider(ServiceProvider obj);
}
