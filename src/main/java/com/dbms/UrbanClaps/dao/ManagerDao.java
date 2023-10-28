package com.dbms.UrbanClaps.dao;

import com.dbms.UrbanClaps.model.Manager;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ManagerDao {


    List<Manager> getAllManager();
}
