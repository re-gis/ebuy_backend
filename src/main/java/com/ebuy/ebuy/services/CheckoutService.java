package com.ebuy.ebuy.services;

import com.ebuy.ebuy.entities.Cart;
import com.ebuy.ebuy.entities.Order;
import com.ebuy.ebuy.entities.UserDetails;
import com.ebuy.ebuy.payload.ApiResponse;
import com.ebuy.ebuy.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class CheckoutService {
    private final OrderRepository orderRepository;

    @Autowired
    public  CheckoutService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<ApiResponse> checkout(Cart cart, UserDetails userDetails) {
        Order order = new Order();
        order.setCart(cart);
        order.setUserDetails(userDetails);
        order.setCartItems(cart.getItems());
        order.setOrderTime(LocalTime.now());
        order.setTotalPrice(cart.getTotalPrice());
        Order orderToSave = orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(true, "Order saved successfully", orderToSave));
    }
}
