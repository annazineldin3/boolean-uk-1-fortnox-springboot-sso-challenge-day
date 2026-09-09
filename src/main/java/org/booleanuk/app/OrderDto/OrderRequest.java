package org.booleanuk.app.OrderDto;

import java.util.List;

public record OrderRequest(
        Long customerId,
        List<Long> productIds
) {
}