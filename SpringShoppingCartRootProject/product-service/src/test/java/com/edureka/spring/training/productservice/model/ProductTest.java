package com.edureka.spring.training.productservice.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    public void test(){
        Product product = Product.builder()
                .id(1L)
                .name("Samsung8")
                .description("Samsung 8 New Feature")
                .build();


    }

    public class ProductFactory{

        public Product getProduct(String name){
            if(name.equals("phone")){
                return new Phone();
            }
            if(name.equals("TV")){
                return new TV();
            }
            return null;
        }

        private class Phone extends Product{

        }
        private class TV extends Product{

        }
    }

}