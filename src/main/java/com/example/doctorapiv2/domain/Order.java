package com.example.doctorapiv2.domain;

import com.example.doctorapiv2.dto.request.CreateOrderRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String patient;

    @Column
    private Date ordersdate;

    @Column
    private String complaints;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    private User owner;

    @CreatedDate
    private Date createdAt;

    public Order (CreateOrderRequest request, User owner, Doctor doctor){
        this.patient = request.getPatient();
        this.complaints = request.getComplaints();
        this.owner = owner;
        this.doctor = doctor;
        try {
            this.ordersdate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getOrdersdate());
        } catch (ParseException err) {
            err.printStackTrace();
        }
    }
}
