package com.dbms.UrbanClaps.controller;

import com.dbms.UrbanClaps.model.Slot;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("provider")
public class ProviderController {


    @GetMapping("slots")
    public List<Slot> findMySlots(@RequestParam Long id){
        return null;
    }


//    See all the Slot of that particular Provider
////    Usss bande ki khud ki info -> Info Change Karne ka option
//
//    Selected Slot maain kis bande pe jana hai
//
}
