package com.example.doctorapiv2.controllers;

import com.example.doctorapiv2.domain.Order;
import com.example.doctorapiv2.dto.request.CreateOrderRequest;
import com.example.doctorapiv2.dto.request.GetOrderRequest;
import com.example.doctorapiv2.dto.response.GetUserOrdersResponse;
import com.example.doctorapiv2.dto.request.UpdateOrderRequest;
import com.example.doctorapiv2.service.DoctorService;
import com.example.doctorapiv2.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@CrossOrigin
public class OrderController {
    private final OrderService orderService;
    private final DoctorService doctorService;

    @GetMapping("/getAllUserOrders")
    public ResponseEntity<GetUserOrdersResponse> getOrders(GetOrderRequest request) {
        val orders = orderService.getUserOrders(request);
        val doctors = doctorService.getAll();
        return ResponseEntity.ok(new GetUserOrdersResponse(orders, doctors));
    }
    @PostMapping("/addNewOrder")
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request){
        return ResponseEntity.ok(orderService.save(request));
    }
    @PatchMapping("/updateUserOrder")
    public ResponseEntity<String> updateOrder(@RequestBody UpdateOrderRequest request){
        return ResponseEntity.ok(orderService.update(request));
    }
    @DeleteMapping("/deleteUsersOrder")
    public ResponseEntity<String> deleteOrder(@RequestParam("id") String id){
        return ResponseEntity.ok(orderService.delete(id));
    }
}
