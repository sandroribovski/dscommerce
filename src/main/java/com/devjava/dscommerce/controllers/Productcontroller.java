package com.devjava.dscommerce.controllers;

import com.devjava.dscommerce.dto.ProductDTO;
import com.devjava.dscommerce.entities.Product;
import com.devjava.dscommerce.repositories.ProductRepository;
import com.devjava.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class Productcontroller {

    @Autowired
    private ProductService service;



    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) {
       return service.findById(id);


    }
}
