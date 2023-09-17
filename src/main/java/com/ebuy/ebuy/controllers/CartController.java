package com.ebuy.ebuy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebuy.ebuy.entities.Cart;
import com.ebuy.ebuy.payload.ApiResponse;
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

    @GetMapping
    public ResponseEntity<ApiResponse> getCart() {
        Cart cart = this.cartService.getCart();
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(true, "Cart fetched successfully...", cart));
    }

    @PostMapping("/checkout")
    public void checkout() {
        this.cartService.checkout();
    }

}
