package org.booleanuk.app.OrderDto;

import org.booleanuk.app.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        LocalDateTime createdAt,
        BigDecimal totalAmount,
        Long customerId,
        List<Long> productIds
) {
    public static OrderResponse fromEntity(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCreatedAt(),
                order.getTotalAmount(),
                order.getCustomer().getId(),
                order.getProducts().stream().map(product -> product.getId()).toList()
        );
    }
}
