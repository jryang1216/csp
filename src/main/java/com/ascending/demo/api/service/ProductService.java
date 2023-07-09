package com.ascending.demo.api.service;

import com.ascending.demo.api.dao.ProductDao;
import com.ascending.demo.api.dto.ProductDto;
import com.ascending.demo.api.entity.Product;

import java.util.List;

public interface ProductService {
    ProductDto save(ProductDto productDto);
    ProductDto update(ProductDto productDto);
    boolean delete(ProductDto productDto);
    boolean deleteById(Long productId);
    List<ProductDto> getProducts();

    List<ProductDto> getProductsAndSuppliersByProductId(Long productId);
}
