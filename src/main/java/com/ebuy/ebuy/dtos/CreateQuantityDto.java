package com.ebuy.ebuy.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ebuy.ebuy.Enums.OperationEnum;

import lombok.Data;

@Data
public class CreateQuantityDto {
    @NotNull(message = "Product ID is required!")
    private Long productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotEmpty(message = "Operation is required!")
    private OperationEnum operation;
}
