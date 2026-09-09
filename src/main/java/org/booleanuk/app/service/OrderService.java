package org.booleanuk.app.service;

import org.booleanuk.app.OrderDto.OrderRequest;
import org.booleanuk.app.OrderDto.OrderResponse;
import org.booleanuk.app.model.Customer;
import org.booleanuk.app.model.Order;
import org.booleanuk.app.model.Product;
import org.booleanuk.app.repository.CustomerRepo;
import org.booleanuk.app.repository.OrderRepo;
import org.booleanuk.app.repository.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;

    public OrderService(OrderRepo orderRepo, CustomerRepo customerRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
    }

    public List<OrderResponse> getAll() {
        return orderRepo.findAll().stream()
                .map(OrderResponse::fromEntity)
                .toList();
    }

    public OrderResponse getById(Long id) {
        return OrderResponse.fromEntity(findEntity(id));
    }

    public OrderResponse create(OrderRequest request) {
        Customer customer = findCustomer(request.customerId());
        List<Product> products = productRepo.findAllById(request.productIds());

        Order order = new Order(sumPrices(products), customer);
        order.setProducts(products);

        return OrderResponse.fromEntity(orderRepo.save(order));
    }

    public OrderResponse update(Long id, OrderRequest request) {
        Order existing = findEntity(id);
        Customer customer = findCustomer(request.customerId());
        List<Product> products = productRepo.findAllById(request.productIds());

        existing.setCustomer(customer);
        existing.setProducts(products);
        existing.setTotalAmount(sumPrices(products));

        return OrderResponse.fromEntity(orderRepo.save(existing));
    }

    public void delete(Long id) {
        orderRepo.delete(findEntity(id));
    }

    private BigDecimal sumPrices(List<Product> products) {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Order findEntity(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    private Customer findCustomer(Long customerId) {
        return customerRepo.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }
}
