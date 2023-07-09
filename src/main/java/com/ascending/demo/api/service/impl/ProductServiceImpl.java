package com.ascending.demo.api.service.impl;

import com.ascending.demo.api.dao.ProductDao;
import com.ascending.demo.api.dao.SuppliersDao;
import com.ascending.demo.api.dao.UsersDao;
import com.ascending.demo.api.daoimpl.springdatajpa.ProductDaoSpringDataJPAImpl;
import com.ascending.demo.api.dto.ProductDto;
import com.ascending.demo.api.dto.SuppliersDto;
import com.ascending.demo.api.entity.Product;
import com.ascending.demo.api.entity.Suppliers;
import com.ascending.demo.api.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    @Qualifier("productDaoSpringDataJPAImpl")
    private ProductDao productDao;
    @Autowired
    @Qualifier("suppliersDaoSpringDataJPAImpl")
    private SuppliersDao suppliersDao;

    @Autowired
    @Qualifier("usersDaoSpringDataJPAImpl")
    private UsersDao usersDao;

    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = productDto.convertProductDtoToProduct();
        Product savedProduct = productDao.save(product);
        ProductDto savedProductDto = savedProduct.convertProductsToProductsDto();
        return savedProductDto;
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        return null;
    }

    @Override
    public boolean delete(ProductDto productDto) {
        return false;
    }

    @Override
    public boolean deleteById(Long productId) {
        return false;
    }

    @Override
    public List<ProductDto> getProducts() {
        return null;
    }

    @Override
    public List<ProductDto> getProductsAndSuppliersByProductId(Long productId) {
        return null;
    }
}
