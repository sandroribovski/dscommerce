package com.devjava.dscommerce.services;


import com.devjava.dscommerce.dto.ProductDTO;
import com.devjava.dscommerce.entities.Product;
import com.devjava.dscommerce.repositories.ProductRepository;
import com.devjava.dscommerce.services.exceptions.DatabaseException;
import com.devjava.dscommerce.services.exceptions.ResorceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        log.info("Realizando o findById do id: {}", id);
        Product product = repository.findById(id).orElseThrow(
                () -> new ResorceNotFoundException("Recurso não encontrado"));
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        log.info("Realizando o findAll");
        Page<Product> result = repository.findAll(pageable);
        return result.map(x -> new ProductDTO(x));
    }


    @Transactional()
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);

        return new ProductDTO(entity);

    }

    @Transactional()
    public ProductDTO update(Long id, ProductDTO dto) {
        log.info("Realizando o update do id: {}", id);

        try{
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResorceNotFoundException("Recurso não encontrado");
        }

    }


    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        log.info("Realizando delete do id: {}", id);
        if (!repository.existsById(id)) {
            throw new ResorceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        log.info("Realizando Insert do produto: {}", dto.getName());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }

}
