package com.ascending.demo.api.entity;

import com.ascending.demo.api.dto.ProductDto;
import com.ascending.demo.api.dto.SuppliersDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "product")
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column (name = "category")
    private String category;
    @Column (name = "name")
    private String name;

    @ManyToMany(mappedBy = "productList", fetch = FetchType.LAZY)
    private Set<Suppliers> suppliersSet;

    public Set<Suppliers> getSuppliers() {
        if (suppliersSet == null)
            suppliersSet = new HashSet<>();
        return suppliersSet;
    }

    public void setSuppliersSet(Set<Suppliers> suppliersSet) {
        this.suppliersSet = suppliersSet;
    }
    public void addSuppliers(Suppliers suppliers){
        suppliers.getProducts().add(this);
        this.getSuppliers().add(suppliers);
    }
    public void removeSuppliers(Suppliers suppliers) {
        this.getSuppliers().remove(suppliers);
        suppliers.getProducts().remove(this);
    }

//    public boolean removeSuppliers(Suppliers suppliers) {
//        boolean successfulFlag = suppliers.getProducts().remove(this);
//        successfulFlag = this.getSuppliers().remove(suppliers);
//        return successfulFlag;
//    }


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
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Suppliers> getSuppliersSet() {
        return suppliersSet;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", suppliersSet=" + suppliersSet +
                '}';
    }
    public ProductDto convertProductsToProductsDto() {
        ProductDto productDto = new ProductDto();
        productDto.setId(productDto.getId());
        productDto.setCategory(getCategory());
        productDto.setName(getName());
        return productDto;
    }

}
