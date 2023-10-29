package com.dbms.UrbanClaps.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {
    private Long id;
    private String bookedTime;
    private String bookedDate;
    private String status;
    private Long slot;

    public Orders(String bookedTime, String bookedDate, String status, Long slot) {
        this.bookedTime = bookedTime;
        this.bookedDate = bookedDate;
        this.status = status;
        this.slot = slot;
    }
}
