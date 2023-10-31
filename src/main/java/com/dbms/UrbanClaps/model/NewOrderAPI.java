package com.dbms.UrbanClaps.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewOrderAPI {

    private Long order_id;
    private String service_name;
    private Long user_id;
    private String slot_date;
    private String slot_time;

}
