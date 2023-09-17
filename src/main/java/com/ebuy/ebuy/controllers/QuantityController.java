package com.ebuy.ebuy.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebuy.ebuy.dtos.CreateQuantityDto;
import com.ebuy.ebuy.payload.ApiResponse;
import com.ebuy.ebuy.services.QuantityService;

@RestController
@RequestMapping("/api/v1/quantity")
public class QuantityController {
    private final QuantityService quantityService;

    public QuantityController(QuantityService quantityService) {
        this.quantityService = quantityService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createQuantity(@RequestBody CreateQuantityDto createQuantityDto) {
        return quantityService.createQuantity(createQuantityDto);
    }
}
