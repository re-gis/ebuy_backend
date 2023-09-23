package com.ebuy.ebuy.repository;

import com.ebuy.ebuy.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
