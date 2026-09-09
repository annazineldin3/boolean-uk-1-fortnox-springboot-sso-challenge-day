package org.booleanuk.app.controller;

import org.apache.coyote.Response;
import org.booleanuk.app.CustomerDto.CustomerRequest;
import org.booleanuk.app.CustomerDto.CustomerResponse;
import org.booleanuk.app.model.Customer;
import org.booleanuk.app.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerResponse> getAll(){
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable Long id){
        return customerService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse create(@RequestBody CustomerRequest request){
        return customerService.create(request);
    }

    @PutMapping("/{id}")
    public CustomerResponse update(
            @PathVariable Long id,
            @RequestBody CustomerRequest request
    ) {
        return customerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        customerService.delete(id);
    }
}
