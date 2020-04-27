package com.edureka.spring.training.productservice.service;

import com.edureka.spring.training.productservice.model.Product;
import com.edureka.spring.training.productservice.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@SpringBootTest
class ProductServiceTest {

	@Autowired
    ProductService productService;

//    @BeforeEach
//    public void setup(){
//        productService = new ProductService();
//        productService.productRepository = Mockito.mock(ProductRepository.class);
//    }
//
//    @Test
//    void save() throws JsonProcessingException {
//        Product product = Product.builder()
//                .name("Name")
//                .description("Description")
//                .build();
//        Mockito.when(productService.productRepository.save(Mockito.any())).thenReturn(new Product());
//
//        boolean saved = productService.save(product);
//        Assertions.assertThat(saved).isTrue();
//
//    }

	@Test
	void shouldConnectToRedis() {
		Jedis jedis = new Jedis("localhost");
		String pong = jedis.ping();
		Assertions.assertThat(pong).isEqualTo("PONG");
	}

	@Test
	void shouldSaveProduct() throws JsonProcessingException {
		// Given
		Product product = Product.builder().id(121L).name("Iphone11").description("Some Description").build();
		// When
		productService.save(product);
		// Then
		Jedis jedis = new Jedis("localhost");
		String value = jedis.get(product.getName()); // JSON
		ObjectMapper objectMapper = new ObjectMapper();
		Product readProduct = objectMapper.readValue(value, Product.class);// converting JSON into Class
		Assertions.assertThat(product).isEqualTo(readProduct);
	}

	
//	@Test
//	void isExists() {
//		Product product = Product.builder().name("Name").description("Description").build();
//
//		Mockito.when(productService.productRepository.save(Mockito.any())).thenReturn(new Product());
//
//		productService.productRepository.save(product);
//		System.out.println("product : " + product.getName());
//		System.out.println(productService.productRepository.findByName(product.getName()));
//		System.out.println("productService : " + productService.isExists(product.getName()));
//		System.out.println(product.getName()); //
//		System.out.println(productService.productRepository.findByName("Name"));
//		Optional<Product> productOptional = productService.productRepository.findByName(product.getName());
//		System.out.println(productOptional.get().getName());
//		Assertions.assertThat(productOptional.get().getName()).isEqualTo(product.getName());
//		Assertions.assertThat(productService.isExists("Name")).isTrue();
//
//	}
	 
}