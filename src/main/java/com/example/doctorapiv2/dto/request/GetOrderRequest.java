package com.example.doctorapiv2.dto.request;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class GetOrderRequest {
    private String sortMethod;

    private String sortType;

    private String dateWith;

    private String dateFor;

    public Date getDateWith() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(this.dateWith);
        } catch (ParseException err) {
            return null;
        }
    }

    public Date getDateFor() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(this.dateFor);
        } catch (ParseException err) {
            return null;
        }
    }
}
