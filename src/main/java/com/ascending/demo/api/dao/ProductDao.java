package com.ascending.demo.api.dao;

import com.ascending.demo.api.entity.Product;
import com.ascending.demo.api.entity.Users;

import java.util.List;

public interface ProductDao {
    Product save(Product product);
    Product update(Product product);
    boolean delete(Product product);
    boolean deleteById(Long productId);
    List<Product> getProducts();

    List<Product> getProductsAndSuppliersByProductId(Long productId);
}
