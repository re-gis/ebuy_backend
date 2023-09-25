package com.ebuy.ebuy.controllers;

import javax.validation.Valid;

import com.ebuy.ebuy.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ebuy.ebuy.dtos.CreateProductDto;
import com.ebuy.ebuy.payload.ApiResponse;
import com.ebuy.ebuy.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody CreateProductDto productDto) {
        return productService.createProduct(productDto);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getallProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/testing")
    public String test() {
        return "This is a test";
    }
}
