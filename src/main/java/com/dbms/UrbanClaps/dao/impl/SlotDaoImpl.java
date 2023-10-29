package com.dbms.UrbanClaps.dao.impl;

import com.dbms.UrbanClaps.dao.SlotDoa;
import com.dbms.UrbanClaps.model.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class SlotDaoImpl implements SlotDoa {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void createNewSlot(Slot slot) {
        jdbcTemplate.update(
                "INSERT INTO slot (slot_time, slot_date, slot_service, slot_provider, slot_user) " +
                        "VALUES (?, ?, ?, ?, ?)\n",
                slot.getTime(),
                slot.getDate(),
                slot.getService(),
                slot.getProvider(),
                slot.getUser()
                );
        System.out.println(slot);
    }

    @Override
    public Long findFreeProvidersGivenSlot(String date, String time, Long serviceid) {
        List<Long> result = jdbcTemplate.query(
                        "select T.id from \n" +
                        "\t(select p1.id, count(s1.slot_time) as cnt\n" +
                        "\tfrom (select provider_id as id from service_provider sp ,service sv where sv.service_id = ? && sv.service_category = sp.provider_category ) p1 left join \n" +
                        "\t(select * from slot s2 where s2.slot_date = ? and s2.slot_time = ?) s1 on p1.id = s1.slot_provider\n" +
                        "\tgroup by p1.id) as T\n" +
                        "where cnt = 0 \n",
                new RowMapper<Long>() {
                    @Override
                    public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return rs.getLong("id");
                    }
                },
                serviceid,
                date,
                time
        );
        System.out.println(result.toString());
        if(result.isEmpty()){
            return -1L;
        }else{
            return result.get(0);
        }

    }
}
