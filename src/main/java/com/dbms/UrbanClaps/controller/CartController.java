package com.dbms.UrbanClaps.controller;

import com.dbms.UrbanClaps.dao.CartDao;
import com.dbms.UrbanClaps.dao.OrderDao;
import com.dbms.UrbanClaps.model.Cart;
import com.dbms.UrbanClaps.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartDao cartDao;

    @Autowired
    OrderDao orderDao;
//    Payment Option
//    Cart Content

    @GetMapping()
    public ResponseEntity<List<Orders>> getCartContents(@RequestParam Long id) {
        try {
            List<Orders> result = orderDao.getOrders(id);
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
