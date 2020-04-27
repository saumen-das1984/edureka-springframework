package com.edureka.spring.training.productservice.repository;

import com.edureka.spring.training.productservice.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void shouldSave(){

    	//Create Product Object
        Product product = Product.builder()
                .name("Some name")
                .description("Some description")
                .build();

        //Save product object
        productRepository.save(product);

        //Verify created product object
        Assertions.assertThat(productRepository.findAll().stream().findAny().get())
                .isEqualTo(product);


    }

    @Test
    public void shouldCheckAProductByName(){
        Product product = Product.builder()
                .name("Samsung8")
                .description("Samsung 8 New Feature")
                .build();

        productRepository.save(product);

        Optional<Product> productOptional = productRepository.findByName(product.getName());

        Assertions.assertThat(productOptional.get()).isEqualTo(product);
    }


}