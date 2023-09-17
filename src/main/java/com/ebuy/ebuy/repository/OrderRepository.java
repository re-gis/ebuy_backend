package com.ebuy.ebuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebuy.ebuy.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
