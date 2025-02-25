package com.example.demo.controller;
import com.example.demo.Controller.ProductController;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
    @WebMvcTest(ProductController.class)
    @AutoConfigureMockMvc
    @ExtendWith(SpringExtension.class)
    class ProductControllerTest {
        @Autowired
        private MockMvc mockMvc;
        @MockitoBean
        private ProductService productService;
        @Test
        void testSaveProduct() throws Exception {
            Product product = new Product("Laptop", 1200.0);
            when(productService.saveProduct(any(Product.class))).thenReturn(product);

            mockMvc.perform(post("/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"name\":\"Laptop\",\"price\":1200.0}"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value("Laptop"));
        }
        @Test
        void testGetAllProducts() throws Exception {
            List<Product> products = Arrays.asList(
                    new Product("Laptop", 1200.0),
                    new Product("Phone", 800.0)
            );
            when(productService.getAllProducts()).thenReturn(products);
            mockMvc.perform(get("/products"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()").value(2))
                    .andExpect(jsonPath("$[0].name").value("Laptop"))
                    .andExpect(jsonPath("$[1].name").value("Phone"));
        }
        @Test
        void testDeleteProduct() throws Exception {
            Long productId = 1L;
            when(productService.deleteProduct(anyLong())).thenReturn(null);
            mockMvc.perform(delete("/products/{id}", productId))
                    .andExpect(status().isNoContent());
            verify(productService, times(1)).deleteProduct(productId);
        }
        @Test
        void testUpdateProduct() throws Exception {
            Long productId = 1L;
            Product existingProduct = new Product( "Old Product", 100.0);
            Product updatedProduct = new Product( "Updated Product", 150.0);
            when(productService.updateProduct(eq(productId), any(Product.class))).thenReturn(updatedProduct);
            mockMvc.perform(put("/products/{id}",productId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(updatedProduct)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value("Updated Product"))
                    .andExpect(jsonPath("$.price").value(150.0));
            verify(productService, times(1)).updateProduct(eq(productId), any(Product.class));
            }
        @Test
        void GetProductById() throws Exception{
            Long productId= 1L;
            Product product=new Product("Laptop",20000);
            when(productService.getProductById(productId)).thenReturn(product);
            mockMvc.perform(get("/products/{id}",productId))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()").value(3))
                    .andExpect(jsonPath("$.name").value("Laptop"))
                    .andExpect(jsonPath("$.price").value(20000));
        }


        @Test
        void testProductNotFound_WhenUpdatingNonExistingProduct() throws Exception {
            Long productId = 20L; // Non-existing product ID

            // Sample JSON request body for updating product

            String productJson= new ObjectMapper().writeValueAsString(new Product("Updated Product",100.0));

            mockMvc.perform(put("/products/{id}", productId)
                            .contentType(MediaType.APPLICATION_JSON) // Indicate JSON request
                            .content(productJson)) // Send request body
                    .andExpect(status().isNotFound());

        }
    }

