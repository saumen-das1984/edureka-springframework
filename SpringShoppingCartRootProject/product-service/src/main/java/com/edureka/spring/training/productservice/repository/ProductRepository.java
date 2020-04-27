package com.edureka.spring.training.productservice.repository;

import com.edureka.spring.training.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {
    //Find by name is converted into query native
    Optional<Product> findByName(String name);
}
