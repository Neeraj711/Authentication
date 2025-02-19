package com.example.couchdb.service;
import com.example.couchdb.Entity.Product;
import com.example.couchdb.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void save(Product product) {
        productRepository.add(product);
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }


    public void deleteProductById(String id) {
        productRepository.deleteById(id);
    }

    public void update(Product product) {
        productRepository.update(product);
    }

    public Product updateProduct(String id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            productRepository.updateProduct(existingProduct);
            return existingProduct;
        }
        return null;
    }
}