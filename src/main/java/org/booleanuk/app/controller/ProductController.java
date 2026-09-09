package org.booleanuk.app.controller;


import org.booleanuk.app.dto.ProductRequest;
import org.booleanuk.app.dto.ProductResponse;
import org.booleanuk.app.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("public/products")
    public List<ProductResponse> getAll(){
        return productService.getAll();
    }

    @GetMapping("public/products/{id}")
    public ProductResponse getById(@PathVariable Long id){
        return productService.getById(id);
    }

    @PostMapping("products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody ProductRequest request){
        return productService.create(request);
    }

    @PutMapping("products/{id}")
    public ProductResponse update(@PathVariable Long id, @RequestBody ProductRequest request){
        return productService.update(id, request);
    }

    @DeleteMapping("products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }
}
