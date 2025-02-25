package com.example.demo.model;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductTest {


    @Test
    void ProductConstructorTest() {
        String Name="Neeraj";
          double Price=270.00;
      Product  product =new Product(Name,Price);
      assertEquals(Name,product.getName());
      assertEquals(Price,product.getPrice());
    }
    @Test
    void testGettersAndSetters() {
        Product product=new Product();

        // Set values
        product.setId(1L);
        product.setName("Tablet");
        product.setPrice(500.0);

        // Assert values
        assertEquals(1L, product.getId());
        assertEquals("Tablet", product.getName());
        assertEquals(500.0, product.getPrice());
    }
}
