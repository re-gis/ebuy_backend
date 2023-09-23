package com.ebuy.ebuy.controllers;

import com.ebuy.ebuy.entities.Cart;
import com.ebuy.ebuy.entities.UserDetails;
import com.ebuy.ebuy.payload.ApiResponse;
import com.ebuy.ebuy.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    @Autowired
    public  CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> checkout(@RequestBody Cart cart, @RequestBody UserDetails userDetails) {
        return checkoutService.checkout(cart, userDetails);
    }
}
