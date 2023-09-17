package com.ebuy.ebuy.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebuy.ebuy.entities.Product;
import com.ebuy.ebuy.repository.ProductRepository;
import com.ebuy.ebuy.entities.CartItem;

@Service
public class CartService {
    private final ProductRepository productRepository;

    private final Map<Long, CartItem> cartItems = new HashMap<>();

    @Autowired
    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String addItemToCart(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));
        if (product.getQuantity() == 0) {
            throw new IllegalArgumentException("Not enough products");
        }

        if (cartItems.containsKey(productId)) {
            CartItem existingItem = cartItems.get(productId);
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            existingItem.setTotalPrice(existingItem.getQuantity() * product.getPrice());
            return "Product added to cart...";
        }
        CartItem newItem = new CartItem(productId, quantity, quantity * product.getPrice());
        cartItems.put(productId, newItem);
        return "Product added to cart successfully...";
    }

}
