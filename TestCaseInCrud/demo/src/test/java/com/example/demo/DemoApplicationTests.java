package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void testMain() {
		// This test ensures that the Spring Boot application context loads properly
		DemoApplication.main(new String[]{});

	}
}
