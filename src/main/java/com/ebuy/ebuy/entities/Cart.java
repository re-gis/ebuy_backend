package com.ebuy.ebuy.entities;

import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User having one cart relationship
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // Cart and cart items relationship
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @Column(name = "date_added")
    private Date dateAdded;

    public Cart() {
        this.dateAdded = new Date();
    }

}

@Entity
@Table(name = "cart_items")
class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One product can be in many carts relationship
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Relationship with the cart since many cart items can be in one carts
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "quantity")
    private Integer quantity;

}
