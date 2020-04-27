package com.edureka.spring.training.productservice.controller;

import com.edureka.spring.training.productservice.model.Product;
import com.edureka.spring.training.productservice.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

//http://localhost:8081/product
@RequestMapping(value = "product")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping
	public ResponseEntity<Boolean> save(@RequestBody Product product) throws JsonProcessingException {
		// TODO - transform productDTO into productDAO
		boolean saved = productService.save(product);
		return ResponseEntity.ok(saved);
	}

	@GetMapping
	public ResponseEntity<Boolean> isExist(@RequestParam String name) {
		boolean exists = productService.isExists(name);
		return ResponseEntity.ok(exists);
	}
}

/*
 * 1. Mobile device make a call //GET, PUT or POST { //Request Payload } 2.
 * Response { //Response
 * 
 * } statusCode : 2XX //server is able to process pefectly 3XX //Dedirected 4XX
 * //Client payload invalid - no valid token 5XX // Server error
 * 
 */