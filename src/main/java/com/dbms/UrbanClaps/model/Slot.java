package com.dbms.UrbanClaps.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Slot {
    private Long id;
    private Timestamp time;
    private Date date;
    private Long service;
    private Long provider;
    private Long user;

    public Slot(Timestamp time, Date date, Long service, Long provider, Long user) {
        this.time = time;
        this.date = date;
        this.service = service;
        this.provider = provider;
        this.user = user;
    }
}
