package com.ebuy.ebuy.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ebuy.ebuy.dtos.CreateProductDto;
import com.ebuy.ebuy.entities.Product;
import com.ebuy.ebuy.payload.ApiResponse;
import com.ebuy.ebuy.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<ApiResponse> createProduct(@Valid CreateProductDto productDto) {
        if (productDto.getProductName() == null || productDto.getImageUrl() == null
                || productDto.getDescription() == null || productDto.getPrice() == null
                || productDto.getQuantity() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.fail(false, "All product details are required!"));
        }
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setQuantity(productDto.getQuantity());
        productRepository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(true, "Product added successfully", product));
    }
}
