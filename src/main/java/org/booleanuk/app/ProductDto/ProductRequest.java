package org.booleanuk.app.ProductDto;

import java.math.BigDecimal;

public record ProductRequest(String name, BigDecimal price) {
}
