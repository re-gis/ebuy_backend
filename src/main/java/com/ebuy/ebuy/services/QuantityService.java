package com.ebuy.ebuy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ebuy.ebuy.Enums.OperationEnum;
import com.ebuy.ebuy.dtos.CreateQuantityDto;
import com.ebuy.ebuy.entities.Product;
import com.ebuy.ebuy.entities.Quantity;
import com.ebuy.ebuy.payload.ApiResponse;
import com.ebuy.ebuy.repository.ProductRepository;
import com.ebuy.ebuy.repository.QuantityRepository;

@Service
public class QuantityService {
    private final ProductRepository productRepository;
    private final QuantityRepository quantityRepository;

    @Autowired
    public QuantityService(ProductRepository productRepository, QuantityRepository quantityRepository) {
        this.productRepository = productRepository;
        this.quantityRepository = quantityRepository;
    }

    public ResponseEntity<ApiResponse> createQuantity(CreateQuantityDto createQuantityDto) {
        if (createQuantityDto.getOperation() == null || createQuantityDto.getQuantity() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.fail(false, "All quantity details are required"));
        }
        Product product = this.productRepository.findById(createQuantityDto.getProductId()).orElseThrow(
                () -> new IllegalArgumentException("Product not found with ID: " + createQuantityDto.getProductId()));
        // Quantity entry of the product
        Quantity existingQuantity = this.quantityRepository.findByProduct(product);
        if (existingQuantity != null) {
            if (createQuantityDto.getOperation() == OperationEnum.ADD) {
                Integer newQuantity = existingQuantity.getQuantity() + createQuantityDto.getQuantity();
                existingQuantity.setQuantity(newQuantity);
                quantityRepository.save(existingQuantity);
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(ApiResponse.success(true, "Quantity updated successfully..."));
            } else {
                Integer newQuantity = existingQuantity.getQuantity() - createQuantityDto.getQuantity();
                existingQuantity.setQuantity(newQuantity);
                quantityRepository.save(existingQuantity);
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(ApiResponse.success(true, "Quantity updated successfully..."));
            }

        } else {

            // If it is new
            Quantity quantity = new Quantity();
            quantity.setOperation(createQuantityDto.getOperation());
            quantity.setProduct(product);
            quantity.setQuantity(createQuantityDto.getQuantity());
            quantityRepository.save(quantity);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success(true, "Quantity created successfully"));
        }
    }
}
