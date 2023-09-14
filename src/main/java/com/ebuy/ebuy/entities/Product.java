package com.ebuy.ebuy.entities;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "date_added")
    private Date date_added;

    @Column(name = "date_updated")
    private Date date_updated;

    public Product() {
    }

    @PrePersist
    protected void onCreate() {
        this.date_added = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.date_updated = new Date();
    }
}
