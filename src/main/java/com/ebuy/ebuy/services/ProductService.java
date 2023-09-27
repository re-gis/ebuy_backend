package com.ebuy.ebuy.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ebuy.ebuy.dtos.CreateProductDto;
import com.ebuy.ebuy.entities.Product;
import com.ebuy.ebuy.payload.ApiResponse;
import com.ebuy.ebuy.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

   
}
