package com.ebuy.ebuy.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CreateProductDto {
    @NotBlank(message = "Product name is required!")
    private String productName;

    @NotBlank(message = "Product description is required!")
    private String description;

    @Positive(message = "Price is positive")
    private Double price;

    @NotBlank(message = "Product picture must be uploaded")
    private String imageUrl;

    @Positive(message = "Product quantity required")
    private int quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
