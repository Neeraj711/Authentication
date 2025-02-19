package com.example.couchdb.Controller;
import com.example.couchdb.Entity.Product;
import com.example.couchdb.service.ProductService;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import com.example.couchdb.Repository.ProductRepository;
import java.util.List;

@RestController
@RequestMapping("/v1/sms")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public void save(@RequestBody Product product) {
        productService.save(product);
    }

    @GetMapping("/subscribe")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @PutMapping("/{id}/extrachaqge")
    public void update(@PathVariable String id, @RequestBody Product product) {
        product.setId(id); // Ensure the correct ID is set
        productService.update(product);
    }
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProductById(id);
        return "Product with ID " + id + " deleted successfully!";
    }
}
