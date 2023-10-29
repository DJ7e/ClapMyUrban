package com.dbms.UrbanClaps.controller;

import com.dbms.UrbanClaps.dao.CartDao;
import com.dbms.UrbanClaps.dao.OrderDao;
import com.dbms.UrbanClaps.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart")
@CrossOrigin(origins = "http://localhost:3000/")
public class CartController {

    @Autowired
    CartDao cartDao;

    @Autowired
    OrderDao orderDao;
//    Payment Option
//    Cart Content

    @GetMapping("byuser")
    public ResponseEntity<List<Orders>> getCartContentsByUserId(@RequestParam Long userid) {
        try {
            List<Orders> result = orderDao.getOrdersByUserId(userid);
            if(result.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("byprovider")
    public ResponseEntity<List<Orders>> getCartContentsByProviderId(@RequestParam Long providerId) {
        try {
            List<Orders> result = orderDao.getOrdersByProviderId(providerId);
            if(result.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
