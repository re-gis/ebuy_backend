package com.ebuy.ebuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebuy.ebuy.entities.Product;
import com.ebuy.ebuy.entities.Quantity;

public interface QuantityRepository extends JpaRepository<Quantity, Long> {

    Quantity findByProduct(Product product);

}
