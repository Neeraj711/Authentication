package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
    @Service
    public class ProductServiceImpl implements ProductService {
        @Autowired
        private ProductRepository productRepository;

        public Product saveProduct(Product product) {
            return productRepository.save(product);
        }

        public List<Product> getAllProducts() {
            return productRepository.findAll();
        }
        @Override
        public Product deleteProduct(Long id) {
       productRepository.deleteById(id);
            return null;
        }
        public Product getProductById(Long id) {
            return productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        }
        public Product updateProduct(Long id, Product updatedProduct) {
            return productRepository.findById(id)
                    .map(existingProduct -> {
                        existingProduct.setName(updatedProduct.getName());
                        existingProduct.setPrice(updatedProduct.getPrice());
                        return productRepository.save(existingProduct);
                    })
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        }
    }

