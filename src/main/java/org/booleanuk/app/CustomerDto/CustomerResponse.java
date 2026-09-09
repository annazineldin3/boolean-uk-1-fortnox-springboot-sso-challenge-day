package org.booleanuk.app.CustomerDto;
import org.booleanuk.app.model.Customer;

public record CustomerResponse(Long id, String name, String email) {

    public static CustomerResponse fromEntity(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail());
    }
}