package com.example.camai.entity;

import lombok.Data;

@Data
public class MyRequestBody {
    private Integer applicationId;

    private String fromDate;

    private String toDate;
}
