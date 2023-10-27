package com.dbms.UrbanClaps.dao.impl;

import com.dbms.UrbanClaps.dao.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CartDaoImpl implements CartDao {

    @Autowired
    JdbcTemplate jdbcTemplate;



}
