package com.dbms.UrbanClaps.controller;

import com.dbms.UrbanClaps.dao.SlotDoa;
import com.dbms.UrbanClaps.model.NewOrderAPI;
import com.dbms.UrbanClaps.model.ServiceProvider;
import com.dbms.UrbanClaps.model.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("provider")
@CrossOrigin(origins = "http://localhost:3000/")
public class ProviderController {

    @Autowired
    SlotDoa slotDoa;

    @GetMapping("slots")
    public ResponseEntity<List<Slot>> findMySlots(@RequestParam Long id){
        try {
            if(id == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            else{
                List<Slot> check = slotDoa.getSlotByID(id);
                return new ResponseEntity<>(check,HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("lodabc")
    public ResponseEntity<List<NewOrderAPI>> kuchNewApi(@RequestParam Long providerId){
        try {
            if(providerId == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            else{
                List<NewOrderAPI> check = slotDoa.getOrderBysome(providerId);
                return new ResponseEntity<>(check,HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



//    @GetMapping("orders")


//    See all the Slot of that particular Provider
////    Usss bande ki khud ki info -> Info Change Karne ka option
//
//    Selected Slot maain kis bande pe jana hai

}
