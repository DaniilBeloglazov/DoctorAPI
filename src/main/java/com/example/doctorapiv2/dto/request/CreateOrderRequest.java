package com.example.doctorapiv2.dto.request;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Data
public class CreateOrderRequest {

    private String patient;

    private String ordersdate;

    private String complaints;

    private String doctorId;

    public Long getDoctorId() {
        return Long.valueOf(doctorId);
    }
}
