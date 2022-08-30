package com.example.doctorapiv2.repository;

import com.example.doctorapiv2.domain.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOwnerId(Long id, Sort sort);
    List<Order> findAllByOwnerIdAndOrdersdateBetween(Long id, Date dateWith, Date dateFor, Sort sort);
}
