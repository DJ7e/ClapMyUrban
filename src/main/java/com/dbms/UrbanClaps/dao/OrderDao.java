package com.dbms.UrbanClaps.dao;

import com.dbms.UrbanClaps.model.Orders;
import com.dbms.UrbanClaps.model.Slot;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {

    List<Orders> getOrders(Long id);


    int createOrder(Slot slot);
}
