package org.booleanuk.app.service;

import org.booleanuk.app.CustomerDto.CustomerRequest;
import org.booleanuk.app.CustomerDto.CustomerResponse;
import org.booleanuk.app.CustomerDto.CustomerValueResponse;
import org.booleanuk.app.model.Customer;
import org.booleanuk.app.model.Order;
import org.booleanuk.app.model.Product;
import org.booleanuk.app.repository.CustomerRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo){
        this.customerRepo = customerRepo;
    }

    public List<CustomerResponse> getAll(){
        return customerRepo.findAll().stream()
                .map(CustomerResponse::fromEntity)
                .toList();
    }

    public CustomerResponse getById(Long id){
        return CustomerResponse.fromEntity(findEntity(id));
    }

    public CustomerResponse create(CustomerRequest request){
        Customer customer = new Customer(request.name(), request.email());
        return CustomerResponse.fromEntity(customerRepo.save(customer));
    }
    public CustomerResponse update(Long id, CustomerRequest request){
        Customer existing = findEntity(id);
        existing.setName(request.name());
        existing.setEmail(request.email());

        return CustomerResponse.fromEntity(customerRepo.save(existing));
    }

    public void delete(Long id){
        customerRepo.delete(findEntity(id));
    }

    private Customer findEntity(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Customer not found"
                        )
                );
    }

    public List<CustomerValueResponse> getCustomerValues() {
        return customerRepo.findAll().stream()
                .map(customer -> {

                    BigDecimal totalValue = customer.getOrders().stream()
                            .map(Order::getTotalAmount)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    return new CustomerValueResponse(
                            customer.getId(),
                            customer.getName(),
                            totalValue
                    );
                })
                .toList();
    }

}




