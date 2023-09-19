package com.ebuy.ebuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebuy.ebuy.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
