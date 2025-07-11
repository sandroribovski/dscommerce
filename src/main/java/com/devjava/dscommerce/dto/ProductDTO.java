package com.devjava.dscommerce.dto;

import com.devjava.dscommerce.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public class ProductDTO {

    private Long id;

    @Size(min = 3, max = 80, message = "O tamanho deve estar entre 3 e 80 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String name;
    @Size(min = 10, message = "O tamanho deve ser no mínimo 10 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String description;
    @Positive(message = "O preço deve ser um valor positivo")
    private Double price;
    private String imgUrl;


    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

   public String getImgUrl() {
        return imgUrl;
    }
}
