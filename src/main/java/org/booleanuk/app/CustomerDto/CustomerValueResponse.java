package org.booleanuk.app.CustomerDto;

import java.math.BigDecimal;

public record CustomerValueResponse(
        Long id,
        String name,
        BigDecimal totalValue
) {
}