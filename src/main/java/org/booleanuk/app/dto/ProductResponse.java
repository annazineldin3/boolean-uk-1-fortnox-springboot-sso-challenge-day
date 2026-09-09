package org.booleanuk.app.dto;

import org.booleanuk.app.model.Product;

import java.math.BigDecimal;

public record ProductResponse(Long id, String name, BigDecimal price) {

    public static ProductResponse fromEntity(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }
}
