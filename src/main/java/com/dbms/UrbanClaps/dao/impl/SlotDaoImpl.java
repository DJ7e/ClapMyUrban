package com.dbms.UrbanClaps.dao.impl;

import com.dbms.UrbanClaps.dao.SlotDoa;
import com.dbms.UrbanClaps.model.ServiceProvider;
import com.dbms.UrbanClaps.model.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;
import java.util.Objects;

@Component
public class SlotDaoImpl implements SlotDoa {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Long createNewSlot(Slot slot) {
        /*jdbcTemplate.update(
                "INSERT INTO slot (slot_time, slot_date, slot_service, slot_provider, slot_user) " +
                        "VALUES (?, ?, ?, ?, ?)\n",
                slot.getTime(),
                slot.getDate(),
                slot.getService(),
                slot.getProvider(),
                slot.getUser()
                );
        System.out.println(slot);*/

        String sql = "INSERT INTO slot (slot_time, slot_date, slot_service, slot_provider, slot_user) " + "VALUES (?, ?, ?, ?, ?)\n";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, slot.getTime());
            ps.setString(2, slot.getDate());
            ps.setLong(3, slot.getService());
            ps.setLong(4, slot.getProvider());
            ps.setLong(5, slot.getUser());
            return ps;
        }, keyHolder);

        return (long) Objects.requireNonNull(keyHolder.getKey()).intValue();

    }

    @Override
    public List<Slot> getSlotByID(Long id) {
        List<Slot> result = jdbcTemplate.query(
                "SELECT * FROM slot WHERE slot_id = ?",
                new SlotRowMapper(),
                id
        );
        return result;

    }

    @Override
    public List<Long> findFreeProvidersGivenSlot(String date, String time, Long serviceid) {
        List<Long> result = jdbcTemplate.query("select T.id from \n" + "\t(select p1.id, count(s1.slot_time) as cnt\n" + "\tfrom (select provider_id as id from service_provider sp ,service sv where sv.service_id = ? && sv.service_category = sp.provider_category ) p1 left join \n" + "\t(select * from slot s2 where s2.slot_date = ? and s2.slot_time = ?) s1 on p1.id = s1.slot_provider\n" + "\tgroup by p1.id) as T\n" + "where cnt = 0 \n", new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong("id");
            }
        }, serviceid, date, time);


        System.out.println(result.toString());
        if (result.isEmpty()) {
            return null;
        } else {
            return result;
        }

    }

    public static class SlotRowMapper implements RowMapper<Slot> {
        @Override
        public Slot mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Slot.builder()
                    .id(rs.getLong("slot_id"))
                    .service(rs.getLong("slot_service"))
                    .user(rs.getLong("slot_user"))
                    .provider(rs.getLong("slot_provider"))
                    .time(rs.getString("slot_time"))
                    .date(rs.getString("slot_date"))
                    .build();
        }
    }
}
