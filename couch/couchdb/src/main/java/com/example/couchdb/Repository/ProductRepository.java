package com.example.couchdb.Repository;

import com.example.couchdb.Entity.Product;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.GenerateView;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends CouchDbRepositorySupport<Product> {
    public ProductRepository(CouchDbConnector db) {
        super(Product.class, db);
    }

    @GenerateView
    public Product findById(String id) {
        return get(id);
    }

    public void deleteById(String id) {
        Product product = get(id);
        if (product != null) {
            remove(product);
        }
    }


    public void updateProduct(Product product) {
        update(product);
    }

}

