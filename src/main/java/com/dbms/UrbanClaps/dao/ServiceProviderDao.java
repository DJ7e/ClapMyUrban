package com.dbms.UrbanClaps.dao;

import com.dbms.UrbanClaps.model.ServicesProvided;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProviderDao {

    List<ServicesProvided> findServicesOfferedByProvider(Long providerId);

}
