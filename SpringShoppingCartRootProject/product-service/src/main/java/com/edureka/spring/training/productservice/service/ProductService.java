package com.edureka.spring.training.productservice.service;

import com.edureka.spring.training.productservice.model.Product;
import com.edureka.spring.training.productservice.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    Jedis jedis;

    public boolean save(Product product) throws JsonProcessingException{
    	ObjectMapper objectMapper = new ObjectMapper();
        String valueAsString = objectMapper.writeValueAsString(product);
        jedis.set(product.getName(), valueAsString);

        return productRepository.save(product) != null;
    }

    public boolean isExists(String name){
    	 return productRepository.findByName(name).isPresent();
    }
    
    public Product get(String name){
        String jsonValue = jedis.get(name);
        ObjectMapper objectMapper = new ObjectMapper();
        if(StringUtils.isNotEmpty(jsonValue)){
            try {
                return objectMapper.readValue(jsonValue, Product.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        }else{
            //Very complicate query
            //Product
            //Payment
            //Order
            {
                //Clumsy - non performant
            }

            //Product Id 1 has payment made or not
            //1-Y
            //2-N
            //3-Y
            //4-N
            //1-Made
            //2-NoName


            Optional<Product> byName = productRepository.findByName(name);
            if(!byName.isPresent()){
                throw new ProductNotFoundException();
            }else{
                String valueAsString = "";
                try {
                    valueAsString = objectMapper.writeValueAsString(byName.get());
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                jedis.setex(byName.get().getName(), 1, valueAsString);
            }
            return byName.get();
        }
    }
}
