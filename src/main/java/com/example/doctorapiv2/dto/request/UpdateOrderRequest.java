package com.example.doctorapiv2.dto.request;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class UpdateOrderRequest {
    private String id;
    private String patient;

    private String ordersdate;

    private String complaints;

    private String doctorId;

    public Date getOrdersdate() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(this.ordersdate);
        } catch (ParseException err) {
            return null;
        }
    }

    public Long getDoctorId() {
        return Long.valueOf(doctorId);
    }
}
