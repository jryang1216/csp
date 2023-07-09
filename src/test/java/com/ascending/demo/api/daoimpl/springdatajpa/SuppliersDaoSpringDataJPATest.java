package com.ascending.demo.api.daoimpl.springdatajpa;

import com.ascending.demo.api.dao.ProductDao;
import com.ascending.demo.api.dao.SuppliersDao;
import com.ascending.demo.api.dao.UsersDao;
import com.ascending.demo.api.entity.Suppliers;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SuppliersDaoSpringDataJPATest {
    private Logger logger = LoggerFactory.getLogger(SuppliersDaoSpringDataJPATest.class);
    @Autowired
    @Qualifier(value = "SuppliersDaoSpringDataJPAImpl")
    protected SuppliersDao suppliersDao;

//    @Autowired
//    protected UsersDao usersDao;
//
//    @Autowired
//    protected ProductDao productDao;


    @Test
    public void getSuppliersTest() {
        List<Suppliers> suppliersList = suppliersDao.getSuppliers();
        assertEquals(5, suppliersList.size());
//        for (Suppliers eachSupplier : suppliersList) {
//            try {
//                eachSupplier.getUsersList().size();
//            } catch (LazyInitializationException ex) {
//                assertTrue(ex instanceof LazyInitializationException);
//            }
//        }
    }
}

