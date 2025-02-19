package com.example.couchdb.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.lang.annotation.Documented;
//import org.springframework.data.couchbase.core.mapping.Document;


@JsonIgnoreProperties(ignoreUnknown = true) // Ignores unknown fields
public class Product {

    @Id
    @JsonProperty("_id") // Maps CouchDB "_id" to this field
    private String id;

    @JsonProperty("_rev") // Maps CouchDB "_rev" to this field
    private String rev;

    private String name;
    private double price;

    // Ensure there's a setter for ID
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
