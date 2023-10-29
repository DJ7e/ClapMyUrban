package com.dbms.UrbanClaps.dao;

import com.dbms.UrbanClaps.model.Slot;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotDoa {
    List<Long> findFreeProvidersGivenSlot(String date, String time, Long serviceid);

    Long createNewSlot(Slot slot);

    List<Slot> getSlotByID(Long id);
}
