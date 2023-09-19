package com.ebuy.ebuy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebuy.ebuy.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    Optional<Product> findById(int productId);

}
