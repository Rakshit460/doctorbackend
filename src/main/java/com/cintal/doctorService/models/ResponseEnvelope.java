package com.cintal.doctorService.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEnvelope {
    private Object result;
    private String error;
    private Integer status;
}
