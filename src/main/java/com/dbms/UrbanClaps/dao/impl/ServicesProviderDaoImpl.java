package com.dbms.UrbanClaps.dao.impl;

import com.dbms.UrbanClaps.dao.ServiceProviderDao;
import com.dbms.UrbanClaps.model.ServiceProvider;
import com.dbms.UrbanClaps.model.ServicesProvided;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ServicesProviderDaoImpl implements ServiceProviderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<ServicesProvided> findServicesOfferedByProvider(Long providerId) {
        List<ServicesProvided> result  = jdbcTemplate.query(
                "SELECT s.service_id,s.service_name,s.service_price,s.service_description,s.service_category \n" +
                        "FROM service s,category c,service_provider p\n" +
                        "WHERE s.service_category = c.category_service AND p.provider_category = c.category_id AND p.provider_id = ?",
                new ServicesProvidedDaoImpl.ServicesRowMapper(),
                providerId
        );
        System.out.println(providerId);
        return result;
    }

    @Override
    public List<ServiceProvider> getAllProviders() {
        List<ServiceProvider> result = jdbcTemplate.query(
                "SELECT * FROM service_provider p",
                new ServiceProviderRowMapper()
        );
        return result;
    }

    @Override
    public ResponseEntity<String> createProvider(ServiceProvider obj) {
        jdbcTemplate.update(
                "INSERT INTO service_provider (provider_fname, provider_lname, provider_phone_number, provider_email_id, provider_password, provider_address, provider_description, provider_photo, provider_aadhar_number, provider_aadhar_verification, provider_account_number, provider_IFSC, provider_manager, provider_category)\n" +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                obj.getFirstName(),
                obj.getMiddleName(),
                obj.getLastName(),
                obj.getPhoneNo(),
                obj.getEmailId(),
                obj.getPassword(),
                obj.getAddress(),
                obj.getDescription(),
                obj.getPhoto(),
                obj.getAadharNumber(),
                obj.getAadharVerification(),
                obj.getAccountNo(),
                obj.getIFSC(),
                obj.getManager(),
                obj.getCategory()
        );
        return new ResponseEntity<>("You are in", HttpStatus.CREATED);
    }

    public static class ServiceProviderRowMapper implements RowMapper<ServiceProvider> {
        @Override
        public ServiceProvider mapRow(ResultSet rs, int rowNum) throws SQLException {
            return ServiceProvider.builder()
                    .id(rs.getLong("provider_id"))
                    .firstName(rs.getString("provider_fname"))
                    .middleName(rs.getString("provider_mname"))
                    .lastName(rs.getString("provider_lname"))
                    .phoneNo(rs.getString("provider_phone_number"))
                    .emailId(rs.getString("provider_email_id"))
                    .password(rs.getString("provider_password"))
                    .address(rs.getString("provider_address"))
                    .description(rs.getString("provider_description"))
                    .photo(rs.getString("provider_photo"))
                    .aadharNumber(rs.getString("provider_aadhar_number"))
                    .aadharVerification(rs.getString("provider_aadhar_verification"))
                    .accountNo(rs.getString("provider_account_number"))
                    .IFSC(rs.getString("provider_IFSC"))
                    .manager(rs.getLong("provider_manager"))
                    .category(rs.getLong("provider_category"))
                    .build();
        }
    }}
