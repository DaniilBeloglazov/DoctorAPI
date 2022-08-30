package com.example.doctorapiv2.service;

import com.example.doctorapiv2.domain.Order;
import com.example.doctorapiv2.dto.request.CreateOrderRequest;
import com.example.doctorapiv2.dto.request.GetOrderRequest;
import com.example.doctorapiv2.dto.request.UpdateOrderRequest;
import com.example.doctorapiv2.repository.DoctorRepository;
import com.example.doctorapiv2.repository.OrderRepository;
import com.example.doctorapiv2.repository.UserRepository;
import com.example.doctorapiv2.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;

    public List<Order> getUserOrders(GetOrderRequest request) {
        val dateWith = request.getDateWith();
        val dateFor = request.getDateFor();
        val sortMethod = Sort.by(request.getSortMethod());
        val typedSort = (request.getSortType().equalsIgnoreCase("desc")) ? sortMethod.descending() : sortMethod.ascending();
        val ownerId = userRepository.findByUsername(SecurityUtil.getCurrentUserLogin()).get().getId();
        if (dateWith != null && dateFor != null) {
            return orderRepository.findAllByOwnerIdAndOrdersdateBetween(ownerId, dateWith, dateFor, typedSort);
        }
        return orderRepository.findAllByOwnerId(ownerId, typedSort);
    }

    public Order save(CreateOrderRequest request) {
        val doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor with this id not found"));

        val owner = userRepository.findByUsername(SecurityUtil.getCurrentUserLogin())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        val order = new Order(request, owner, doctor);

        return orderRepository.save(order);
    }

    public String update(UpdateOrderRequest request) {
        val orderToUpdate = orderRepository.findById(Long.valueOf(request.getId()))
                .orElseThrow(() -> new EntityNotFoundException("Order with this id not found"));
        orderToUpdate.setPatient(request.getPatient());
        orderToUpdate.setComplaints(request.getComplaints());
        orderToUpdate.setOrdersdate(request.getOrdersdate());
        val doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor with this id not found"));
        orderToUpdate.setDoctor(doctor);
        orderRepository.save(orderToUpdate);
        return "Order update!";
    }

    public String delete(String id) {
        orderRepository.deleteById(Long.valueOf(id));
        return "Order deleted!";
    }
}
