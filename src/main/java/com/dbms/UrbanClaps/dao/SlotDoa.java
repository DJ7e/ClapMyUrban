package com.dbms.UrbanClaps.dao;

import com.dbms.UrbanClaps.model.Slot;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotDoa {
    Long findFreeProvidersGivenSlot(String date, String time, Long serviceid);

    void createNewSlot(Slot slot);
}
