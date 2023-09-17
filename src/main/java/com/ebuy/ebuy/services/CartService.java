package com.ebuy.ebuy.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebuy.ebuy.entities.Product;
import com.ebuy.ebuy.entities.Quantity;
import com.ebuy.ebuy.repository.OrderRepository;
import com.ebuy.ebuy.repository.ProductRepository;
import com.ebuy.ebuy.repository.QuantityRepository;
import com.ebuy.ebuy.entities.Cart;
import com.ebuy.ebuy.entities.CartItem;
import com.ebuy.ebuy.entities.Order;

@Service
public class CartService {
    private final ProductRepository productRepository;
    private final QuantityRepository quantityRepository;
    private final OrderRepository orderRepository;

    private final Map<Long, CartItem> cartItems = new HashMap<>();

    @Autowired
    public CartService(ProductRepository productRepository, QuantityRepository quantityRepository,
            OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.quantityRepository = quantityRepository;
        this.orderRepository = orderRepository;
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

    public Cart getCart() {
        List<CartItem> cartItemList = new ArrayList<>(cartItems.values());
        Cart cart = new Cart();
        cart.setItems(cartItemList);
        return cart;
    }

    public void checkout() {
        List<Order> orders = new ArrayList<>();
        for (CartItem cartItem : cartItems.values()) {
            Product product = productRepository.findById(cartItem.getProduct().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            Quantity quantity = quantityRepository.findByProduct(product);
            if (quantity != null) {
                Integer availableQuantity = product.getQuantity();
                Integer requestedQuantity = cartItem.getQuantity();

                if (requestedQuantity <= availableQuantity) {
                    quantity.setQuantity(availableQuantity - requestedQuantity);
                    quantityRepository.save(quantity);
                } else {
                    throw new IllegalArgumentException(
                            "Insufficient quantity for product " + cartItem.getProduct().getId());
                }

            } else {
                throw new IllegalArgumentException(
                        "Quantity not found for product with ID: " + cartItem.getProduct().getId());
            }
            // Create order
            Order order = new Order();
            order.setCart(getCart());
            order.setQuantity(cartItem.getQuantity());
            order.setTotal(cartItem.getTotalPrice());
            order.setIsDelivered(false);
            order.setIsPaid(false);
            orders.add(order);
        }
        orderRepository.saveAll(orders);
        cartItems.clear();
    }

}
