package com.dbms.UrbanClaps.controller;

import com.dbms.UrbanClaps.dao.OrderDao;
import com.dbms.UrbanClaps.dao.ServicesProvidedDao;
import com.dbms.UrbanClaps.dao.SlotDoa;
import com.dbms.UrbanClaps.dao.UserDao;
import com.dbms.UrbanClaps.model.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "user")
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    ServicesProvidedDao servicesProvidedDao;

    @Autowired
    SlotDoa slotDoa;

    @Autowired
    OrderDao orderDao;

    @PostMapping("createSlot")
    public ResponseEntity<String> createSlot(@RequestParam String date,@RequestParam String time,@RequestParam Long serviceid){
        try{
            Long userid = (long) 5;

            List<Long> cur = slotDoa.findFreeProvidersGivenSlot(date,time,serviceid);
            if(cur == null){
                return new ResponseEntity<>("No Provider Avaliable at this slot\n" +
                        "Please Select different Slot"
                        ,HttpStatus.NO_CONTENT);
            }else{
                Slot slot = Slot.builder()
                        .user(userid)
                        .id(0L)
                        .time(time)
                        .date(date)
                        .provider(cur.get(0))
                        .service(serviceid)
                        .build();
                Long id = slotDoa.createNewSlot(slot);
                slot.setId(id);
                int a = orderDao.createOrder(slot);
                if(a == 0){
                    return new ResponseEntity<>("Order cannot be created",HttpStatus.INTERNAL_SERVER_ERROR);
                }
                return new ResponseEntity<>("Slot and Order Created",HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println(e.toString());

            e
                    .getCause()
                    .printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

/*
    public int addCustomer(User user){
        String sql = "INSERT INTO User(username,password,firstName,lastName,contact,age,address,dateOfBirth,authority) VALUES(?,?,?,?,?,?,?,?,?)";
        KeyHolder keyholder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4,user.getLastName());
            ps.setString(5,user.getContact());
            ps.setInt(6,user.getAge());
            ps.setString(7,user.getAddress());
            ps.setDate(8,user.getDateOfBirth());
            ps.setString(9, "C");
            return ps;
        }, keyholder);
*/
