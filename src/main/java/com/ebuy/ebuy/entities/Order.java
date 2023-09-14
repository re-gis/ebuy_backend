package com.ebuy.ebuy.entities;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // This is a relationship between order and user since a user can order many
    // orders
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Relationship between an order and cart
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "isPaid")
    private Boolean isPaid;

    @Column(name = "paidAt")
    private Date paidAt;

    @PrePersist
    protected void onPaid() {
        this.paidAt = new Date();
    }

    @Column(name = "isDelivered")
    private Boolean isDelivered;

    @Column(name = "deliveredAt")
    private Date deliveredAt;

    @PreUpdate
    protected void onDeliver() {
        this.deliveredAt = new Date();
    }

}
