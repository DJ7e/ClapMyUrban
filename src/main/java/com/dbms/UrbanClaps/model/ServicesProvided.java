package com.dbms.UrbanClaps.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ServicesProvided {
    private Integer id;
    private String serviceName;
    private Integer servicePrice;
    private String serviceDes;
    private String serviceCategory;
}
