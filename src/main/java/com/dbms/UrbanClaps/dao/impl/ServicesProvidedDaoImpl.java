package com.dbms.UrbanClaps.dao.impl;

import com.dbms.UrbanClaps.dao.ServicesProvidedDao;
import com.dbms.UrbanClaps.model.ServicesProvided;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ServicesProvidedDaoImpl implements ServicesProvidedDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String createService(ServicesProvided servicesProvided){
//        jdbcTemplate.update(
//                "INSERT INTO service VALUES (?,?,?,?,?)",
////                new ServicesRowMapper(),
//                servicesProvided.getId(),
//                servicesProvided.getServiceName(),
//                servicesProvided.getServicePrice(),
//                servicesProvided.getServiceDes(),
//                servicesProvided.getServiceCategory()
//        );
        return "Hello";
    }

    public static class ServicesRowMapper implements RowMapper<ServicesProvided> {
        @Override
        public ServicesProvided mapRow(ResultSet rs, int rowNum) throws SQLException {
            return ServicesProvided.builder()
                    .id(rs.getInt("id"))
                    .serviceName(rs.getString("service_name"))
                    .servicePrice(rs.getInt("service_price"))
                    .serviceDes(rs.getString("service_des"))
                    .serviceCategory(rs.getString("service_category"))
                    .build();
        }
    }

}
