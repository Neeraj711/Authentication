package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig
public class ResourceNotFoundExceptionTest {

     @Test
    void resourceNotfound(){
         String error="Resource Not Found";
         ResourceNotFoundException exception=new ResourceNotFoundException(error);
         assertEquals(error,exception.getMessage());
     }






}
