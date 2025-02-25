package com.example.demo.service;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productService;
    @Test
    void testGetAllProducts() {
        // Arrange: Mock product list
        List<Product> products = Arrays.asList(
                new Product("Laptop", 1200.0),
                new Product("Phone", 800.0)
        );
        when(productRepository.findAll()).thenReturn(products);
        List<Product> result = productService.getAllProducts();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Laptop", result.get(0).getName());
        assertEquals(1200.0, result.get(0).getPrice());
        assertEquals("Phone", result.get(1).getName());
        assertEquals(800.0, result.get(1).getPrice());
        verify(productRepository, times(1)).findAll();
    }
    @Test
    public void testSaveProduct(){
        Product product=new Product("Laptop",20000);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        Product savedProduct=productService.saveProduct(product);
        assertNotNull(savedProduct);
        assertEquals("Laptop", savedProduct.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }
    @Test
    public void testDeleteProduct(){
        Long productId=1L;
        Product product =new Product("Laptop",2000);
//        doNothing().when(productService).deleteProduct(productId);
        Product deleteproduct=productService.deleteProduct(productId);
        assertEquals(null,deleteproduct);
    }
    @Test
    public void testUpdateProduct_Success() {
        Long productId = 1L;
        Product existingProduct = new Product("Laptop", 3000.0);
        Product updatedProduct = new Product("UpdatedLaptop", 25000.0);
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);
        Product result = productService.updateProduct(productId, updatedProduct);
        assertNotNull(result);
        assertEquals("UpdatedLaptop", result.getName());
        assertEquals(25000.0, result.getPrice());
    }

    @Test
    public void testgetProductbyId(){
        Long productId=1L;
        Product existingProduct = new Product("MyLaptop", 3000.0);
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        Product result = productService.getProductById(productId);
        assertNotNull(result);
        assertEquals("MyLaptop",result.getName());
        assertEquals(3000.0,result.getPrice());
    }
}
