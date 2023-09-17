package com.ebuy.ebuy.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebuy.ebuy.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add/{id}/{quantity}")
    public String addItemToCart(@PathVariable("id") Long productId, @PathVariable("quantity") int quantity) {
        return cartService.addItemToCart(productId, quantity);
    }

}
