package com.ascending.demo.api.dao.impl.hibernate;

import com.ascending.demo.api.dao.ProductDao;
import com.ascending.demo.api.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("productDaoHibernateImpl")
public class ProductDaoHibernateImpl implements ProductDao {
    private Logger logger = LoggerFactory.getLogger(ProductDaoHibernateImpl.class);
    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public boolean delete(Product product) {
        return false;
    }

    @Override
    public boolean deleteById(Long productId) {
        return false;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public List<Product> getProductsAndSuppliersByProductId(Long productId) {
        return null;
    }
}
