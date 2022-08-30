package com.example.doctorapiv2.dto.response;

import com.example.doctorapiv2.domain.Doctor;
import com.example.doctorapiv2.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class GetUserOrdersResponse {
    List<Order> orders;
    List<Doctor> doctors;
}
