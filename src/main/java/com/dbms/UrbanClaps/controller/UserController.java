package com.dbms.UrbanClaps.controller;

import com.dbms.UrbanClaps.dao.ServicesProvidedDao;
import com.dbms.UrbanClaps.dao.SlotDoa;
import com.dbms.UrbanClaps.dao.UserDao;
import com.dbms.UrbanClaps.model.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(path = "user")
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    ServicesProvidedDao servicesProvidedDao;

    @Autowired
    SlotDoa slotDoa;

    @PostMapping("createSlot")
    public ResponseEntity<String> createSlot(@RequestParam String date,@RequestParam String time,@RequestParam Long serviceid){
        try{
            Long userid = (long) 5;
            Long cur = null;
//            int i = 0 ;
//            int n = servicesProvidedDao.findAllServices().size();
            cur = slotDoa.findFreeProvidersGivenSlot(date,time,serviceid);

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
                        .provider(cur)
                        .service(serviceid)
                        .build();
                slotDoa.createNewSlot(slot);
                return new ResponseEntity<>(HttpStatus.OK);
            }

        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}
