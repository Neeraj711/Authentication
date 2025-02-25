package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product deleteProduct(Long id);

    Product updateProduct(Long id, Product updatedProduct);
}