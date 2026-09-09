package org.booleanuk.app.controller;

import org.booleanuk.app.OrderDto.OrderRequest;
import org.booleanuk.app.OrderDto.OrderResponse;
import org.booleanuk.app.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("orders")
    public List<OrderResponse> getAll() {
        return orderService.getAll();
    }

    @GetMapping("orders/{id}")
    public OrderResponse getById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @PostMapping("public/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(@RequestBody OrderRequest request) {
        return orderService.create(request);
    }

    @PutMapping("public/orders/{id}")
    public OrderResponse update(@PathVariable Long id, @RequestBody OrderRequest request) {
        return orderService.update(id, request);
    }

    @DeleteMapping("orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }

    @GetMapping("orders/value")
    public List<OrderResponse> getAllOrderedByValue() {
        return orderService.getAllOrderedByValue();
    }
}
