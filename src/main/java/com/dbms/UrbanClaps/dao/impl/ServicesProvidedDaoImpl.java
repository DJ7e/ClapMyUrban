package com.dbms.UrbanClaps.dao.impl;

import com.dbms.UrbanClaps.dao.ServicesProvidedDao;
import com.dbms.UrbanClaps.model.ServicesProvided;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ServicesProvidedDaoImpl implements ServicesProvidedDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String createService(ServicesProvided servicesProvided){
        jdbcTemplate.update(
                "INSERT INTO service VALUES (?,?,?,?,?)",
                servicesProvided.getId(),
                servicesProvided.getName(),
                servicesProvided.getPrice(),
                servicesProvided.getDescription(),
                servicesProvided.getCategory()
        );
        return "Hello";
    }

    @Override
    public List<ServicesProvided> findAllServices(){
        List<ServicesProvided> result  = jdbcTemplate.query(
                "SELECT service_id,service_name,service_price,service_description,service_category FROM service",
                new ServicesRowMapper()
        );
        return result;
    }

    @Override
    public List<ServicesProvided> findServicesByCategory(String category) {
        List<ServicesProvided> result  = jdbcTemplate.query(
                "SELECT s.service_id,s.service_name,s.service_price,s.service_description,s.service_category FROM service s,category c WHERE s.service_id = c.category_id AND c.category_name = ?",
                new ServicesRowMapper(),
                (String)category
        );
        System.out.println(category);
        return result;
    }

    @Override
    public List<ServicesProvided> findServicesByName(String name) {
        List<ServicesProvided> result  = jdbcTemplate.query(
                "SELECT service_id,service_name,service_price,service_description,service_category FROM service WHERE service_name = ?",
                new ServicesRowMapper(),
                name
        );
        return result;
    }

    @Override
    public String changeName(Long id, String name) {
//        jdbcTemplate.update(
//                "UPDATE service SET  "
//        )
        return "BHag";
    }


    public static class ServicesRowMapper implements RowMapper<ServicesProvided> {
        @Override
        public ServicesProvided mapRow(ResultSet rs, int rowNum) throws SQLException {
            return ServicesProvided.builder()
                    .id(rs.getLong("service_id"))
                    .name(rs.getString("service_name"))
                    .price(rs.getLong("service_price"))
                    .description(rs.getString("service_description"))
                    .category(rs.getString("service_category"))
                    .build();
        }
    }




}
