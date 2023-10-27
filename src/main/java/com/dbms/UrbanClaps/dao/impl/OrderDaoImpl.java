package com.dbms.UrbanClaps.dao.impl;

import com.dbms.UrbanClaps.dao.OrderDao;
import com.dbms.UrbanClaps.model.Orders;
import com.dbms.UrbanClaps.model.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Orders> getOrders(Long id) {
        List<Orders> result = jdbcTemplate.query(
                "SELECT o.order_id,o.order_booked_time,o.order_booked_date,o.order_status,o.order_slot FROM orders o,slot s WHERE o.order_slot = s.slot_id AND s.slot_user = ?",
                new OrderRowMapper(),
                id
        );
        return result;
    }

    public static class OrderRowMapper implements RowMapper<Orders> {
        @Override
        public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Orders.builder()
                    .id(rs.getLong("order_id"))
                    .bookedTime(rs.getString("order_booked_time"))
                    .bookedDate(rs.getString("order_booked_date"))
                    .status(rs.getString("order_status"))
                    .slot(rs.getLong("order_slot"))
                    .build();
        }
    }


}
