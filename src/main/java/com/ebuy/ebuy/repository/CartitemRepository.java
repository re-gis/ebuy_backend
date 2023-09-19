package com.ebuy.ebuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebuy.ebuy.entities.CartItem;

public interface CartitemRepository extends JpaRepository<CartItem, Long> {
    
}
