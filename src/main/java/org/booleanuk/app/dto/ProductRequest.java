package org.booleanuk.app.dto;

import java.math.BigDecimal;

public record ProductRequest(String name, BigDecimal price) {
}
