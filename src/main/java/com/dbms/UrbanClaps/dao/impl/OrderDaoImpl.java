package com.dbms.UrbanClaps.dao.impl;

import com.dbms.UrbanClaps.config.Constants;
import com.dbms.UrbanClaps.dao.OrderDao;
import com.dbms.UrbanClaps.model.Orders;
import com.dbms.UrbanClaps.model.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Constants constants;

    @Override
    public List<Orders> getOrdersByUserId(Long id) {
        List<Orders> result = jdbcTemplate.query(
                "SELECT o.order_id,o.order_booked_time,o.order_booked_date,o.order_status,o.order_slot " +
                        "FROM orders o,slot s " +
                        "WHERE o.order_slot = s.slot_id AND s.slot_user = ?",
                new OrderRowMapper(),
                id
        );
        return result;
    }

    @Override
    public int updateOrderStatus(Long id) {
        String sql = "UPDATE orders\n" +
                "SET order_status = 'Completed' " +
                "WHERE order_id = ?";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, id);
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public List<Orders> getOrdersByProviderId(Long id) {
        List<Orders> result = jdbcTemplate.query(
                "SELECT o.order_id,o.order_booked_time,o.order_booked_date,o.order_status,o.order_slot " +
                        "FROM orders o " +
                        "WHERE o.order_id = ?",
                new OrderRowMapper(),
                id
        );
        return result;
    }

    @Override
    public List<Orders> getOrderByOrderId(Long orderId) {
        List<Orders> result = jdbcTemplate.query(
                "SELECT o.order_id,o.order_booked_time,o.order_booked_date,o.order_status,o.order_slot " +
                        "FROM orders o" +
                        "WHERE o.order_slot = s.slot_id AND s.slot_provider = ?",
                new OrderRowMapper(),
                orderId
        );
        return result;
    }

    @Override
    public int createOrder(Slot slot) {
        String localTime = String.valueOf(LocalTime.now());
        String localDate = String.valueOf(LocalDate.now());
        /*Change format*/
        Orders order = Orders
                .builder()
                .id(null)
                .slot(slot.getId())
                .status("PENDING")
                .bookedDate(localDate)
                .bookedTime(localTime)
                .build();
        System.out.println(order);
        int x = jdbcTemplate.update(
                "INSERT INTO orders (order_booked_time, order_booked_date,  order_status, order_slot)\n" +
                        "VALUES (?,?,?,?)",
                order.getBookedTime(),
                order.getBookedDate(),
                order.getStatus(),
                order.getSlot()
        );

        return x;

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
