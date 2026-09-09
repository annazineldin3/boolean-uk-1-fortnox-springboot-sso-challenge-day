package org.booleanuk.app.service;

import org.booleanuk.app.ProductDto.ProductRequest;
import org.booleanuk.app.ProductDto.ProductResponse;
import org.booleanuk.app.model.Order;
import org.booleanuk.app.model.Product;
import org.booleanuk.app.repository.OrderRepo;
import org.booleanuk.app.repository.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    public ProductService(ProductRepo productRepo, OrderRepo orderRepo){
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

   public List<ProductResponse> getAll(){
        return productRepo.findAll().stream()
                .map(ProductResponse::fromEntity)
                .toList();
   }

   public ProductResponse getById(Long id){
        return ProductResponse.fromEntity(findEntity(id));
   }

   public ProductResponse create(ProductRequest request){
        Product product = new Product(request.name(), request.price());
        return ProductResponse.fromEntity(productRepo.save(product));
   }

   public ProductResponse update(Long id, ProductRequest request){
        Product existing = findEntity(id);
        existing.setName(request.name());
        existing.setPrice(request.price());
        return ProductResponse.fromEntity(productRepo.save(existing));
   }

   public void delete(Long id){
        Product product = findEntity(id);
        for (Order order : product.getOrders()){
            order.getProducts().remove(product);
            orderRepo.save(order);
        }
        productRepo.delete(product);
   }

   private Product findEntity(Long id){
        return productRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
   }
}
