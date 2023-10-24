package com.dbms.UrbanClaps.dao.impl;

import com.dbms.UrbanClaps.dao.ServiceProviderDao;
import com.dbms.UrbanClaps.model.ServicesProvided;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServicesProviderDaoImpl implements ServiceProviderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<ServicesProvided> findServicesOfferedByProvider(Long providerId) {
        List<ServicesProvided> result  = jdbcTemplate.query(
                "    SELECT s.service_id,s.service_name,s.service_price,s.service_description,s.service_category \n" +
                        "    FROM service s,category c,service_provider p \n" +
                        "    WHERE s.service_id = c.category_id AND c.category_provider = p.provider_id AND p.provider_id = ?",
                new ServicesProvidedDaoImpl.ServicesRowMapper(),
                providerId
        );
        System.out.println(providerId);
        return result;

    }



}
