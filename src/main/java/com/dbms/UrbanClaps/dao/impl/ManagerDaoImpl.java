package com.dbms.UrbanClaps.dao.impl;

import com.dbms.UrbanClaps.dao.ManagerDao;
import com.dbms.UrbanClaps.model.LoginUser;
import com.dbms.UrbanClaps.model.Manager;
import com.dbms.UrbanClaps.model.ServicesProvided;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ManagerDaoImpl implements ManagerDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Manager> getAllManager() {

        List<Manager> result  = jdbcTemplate.query(
                "SELECT * FROM manager",
                new ManagerRowMapper()
        );
        return result;
    }

    @Override
    public List<LoginUser> getManagerCredentials(LoginUser loginUser) {
        String emailId = loginUser.getUsername();
        List<LoginUser> result = jdbcTemplate.query("select manager_email_id as username,manager_password as password from manager \n" +
                        "where manager_email_id = ?",
                new UserDaoImpl.LoginWURowMapper(),
                emailId
        );
        return result;
    }

    public static class ManagerRowMapper implements RowMapper<Manager> {
        @Override
        public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Manager.builder()
                    .id(rs.getLong("manager_id"))
                    .firstName(rs.getString("manager_fname"))
                    .middleName(rs.getString("manager_mname"))
                    .lastName(rs.getString("manager_lname"))
                    .phoneNo(rs.getString("manager_phone_number"))
                    .emailId(rs.getString("manager_email_id"))
                    .password(rs.getString("manager_password"))
                    .address(rs.getString("manager_address"))
                    .description(rs.getString("manager_description"))
                    .photo(rs.getString("manager_photo"))
                    .build();
        }
    }


}

