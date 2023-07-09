package com.ascending.demo.api.dto;

import com.ascending.demo.api.entity.Product;
import com.ascending.demo.api.entity.Suppliers;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

public class ProductDto {

    private Long id;

    private String category;

    private String name;
    private Set<SuppliersDto> suppliersSet = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SuppliersDto> getSuppliersSet() {
        return suppliersSet;
    }

    public void setSuppliersSet(Set<SuppliersDto> suppliersSet) {
        this.suppliersSet = suppliersSet;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", suppliersSet=" + suppliersSet +
                '}';
    }

    public Product convertProductDtoToProduct() {
        Product product = new Product();
        if (getId() != null)
            product.setId(getId());
            product.setName(getName());
            product.setCategory(getCategory());
        return product;
    }
}
