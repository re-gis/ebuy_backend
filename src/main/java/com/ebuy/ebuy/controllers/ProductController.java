package com.ebuy.ebuy.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebuy.ebuy.dtos.CreateProductDto;
import com.ebuy.ebuy.payload.ApiResponse;
import com.ebuy.ebuy.services.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody CreateProductDto productDto) {
        return productService.createProduct(productDto);
    }
}
