package com.devjava.dscommerce.controllers;

import com.devjava.dscommerce.dto.ProductDTO;
import com.devjava.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class Productcontroller {

    @Autowired
    private ProductService service;



    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) {
       return service.findById(id);
    }

    @GetMapping()
    public Page<ProductDTO> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @PostMapping()
    public ProductDTO insert(@RequestBody ProductDTO dto) {
        return service.insert(dto);
    }

}
