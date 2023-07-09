package com.ascending.demo.api.daoimpl.hibernate;

import com.ascending.demo.api.dao.ProductDao;
import com.ascending.demo.api.dao.SuppliersDao;
import com.ascending.demo.api.dao.UsersDao;
import com.ascending.demo.api.dao.impl.hibernate.ProductDaoHibernateImpl;
import com.ascending.demo.api.dao.impl.hibernate.SuppliersDaoHibernateImpl;
import com.ascending.demo.api.dao.impl.hibernate.UsersDaoHibernateImpl;
import com.ascending.demo.api.entity.Suppliers;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


public class SuppliersDaoHibernateTest {
    private Logger logger = LoggerFactory.getLogger(SuppliersDaoHibernateTest.class);
    private static SuppliersDao suppliersDao;
    private static UsersDao usersDao;
    private static ProductDao productDao;
    @BeforeAll
    public static void setupOnce() {
        suppliersDao = new SuppliersDaoHibernateImpl();
        usersDao = new UsersDaoHibernateImpl();
        productDao = new ProductDaoHibernateImpl();
    }
    @AfterAll
    public static void teardownOnce() {
        suppliersDao = null;
        usersDao = null;
        productDao = null;
    }

    @Test
    public void testSaveSupplier() {
//        Suppliers suppliers = createSupplierByName("abc" + getRandomInt(1,1000));
        Suppliers savedSupplier = suppliersDao.save(suppliers);
        assertNotNull(savedSupplier.getId());
        assertEquals(suppliers.getName(), savedSupplier.getName());
 //       assertEquals(supplier.getDescription(), savedSupplier.getDescription());
    }

    private Suppliers createSupplierByName(String name) {
        Suppliers suppliers = new Suppliers();
        suppliers.setName(name);
        return suppliers;
    }

    private int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
//
    @Test
    public void getSuppliersByIdTest() {
        Suppliers randomSuppliers = getRandomSuppliers();
        if (randomSuppliers == null) {
            logger.error("there is no supplier being found in DB, please double check DB");
        } else {
            Long supplierId = randomSuppliers.getId();
            Suppliers retrievedSuppliers = suppliersDao.getSupplierById(supplierId);
            assertTrue(true);
        }
    }

    private Suppliers getRandomSuppliers() {
        List<Suppliers> suppliersList = suppliersDao.getSuppliers();
        Suppliers randomSuppliers = null;
        if (suppliersList != null && suppliersList.size() > 0) {
            int randomIndex = getRandomInt(0, suppliersList.size());
            randomSuppliers = suppliersList.get(randomIndex);
        }
        return randomSuppliers;
    }

    @Test
    private Suppliers createSupplierByNameTest(String name) {
        Suppliers suppliers = new Suppliers();
        suppliers.setName(name);
        return suppliers;
    }

    @Test
    public void getAllSuppliersTest() {
        List<Suppliers> suppliersList = suppliersDao.getSuppliers();
        assertEquals(5, suppliersList.size());
        for (Suppliers eachSupplier : suppliersList) {
            try {
                eachSupplier.getUsersList().size();
            } catch (LazyInitializationException ex) {
                assertTrue(ex instanceof LazyInitializationException);
            }
        }
    }

    @Test
    public void testGetSuppliersAndUsersBySupplierId(){
        Suppliers retrievedSupplier = suppliersDao.getSuppliersAndUsersBySupplierId((long)2);
        assertEquals(1, retrievedSupplier.getUsersList().size());
    }

    @Test
    public void deleteSupplierWithoutAnyAssociatedUsersTest() {
        Suppliers suppliers = createSupplierByName("name1");
        Suppliers savedSupplier = suppliersDao.save(suppliers);
        boolean deleteSuccessfulFlag = suppliersDao.delete(savedSupplier);
        assertEquals(true, deleteSuccessfulFlag);

        Suppliers retrievedSupplier = suppliersDao.getSupplierById(savedSupplier.getId());
        assertNull(retrievedSupplier);
    }
}
