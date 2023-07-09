package com.ascending.demo.api.daoimpl.springdatajpa;

import com.ascending.demo.api.dao.ProductDao;
import com.ascending.demo.api.repository.ProductRepository;
import com.ascending.demo.api.entity.Product;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("productDaoSpringDataJPAImpl")
public class ProductDaoSpringDataJPAImpl implements ProductDao {
    private Logger logger = LoggerFactory.getLogger(ProductDaoSpringDataJPAImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product update(Product product) {
        Product updatedProduct = productRepository.save(product);
        return updatedProduct;
    }

    @Override
    public boolean delete(Product product) {
        boolean successFlag = false;
        try {
            productRepository.delete(product);
            successFlag = true;
        } catch (IllegalArgumentException iae) {
            logger.error("caught IllegalArgumentException when trying delete product with error = {}", iae.getMessage());
        } catch (OptimisticEntityLockException olfe) {
            logger.error("caught OptimisticEntityLockException when trying delete product with error = {}", olfe.getMessage());
        }
        return successFlag;
    }

    @Override
    public boolean deleteById(Long productId) {
        boolean successFlag = false;
        try {
            productRepository.deleteById(productId);
            successFlag = true;
        } catch (IllegalArgumentException iae) {
            logger.error("caught IllegalArgumentException when trying deleteById with productId = {}, error = {}", productId, iae.getMessage());
        } catch (OptimisticEntityLockException olfe) {
            logger.error("caught OptimisticEntityLockException when trying deleteById with productId = {}, error = {}", productId, olfe.getMessage());
        }
        return successFlag;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsAndSuppliersByProductId(Long productId) {
        return null;
    }
}
