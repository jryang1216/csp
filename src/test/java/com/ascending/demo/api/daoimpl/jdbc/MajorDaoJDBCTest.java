package com.ascending.demo.api.daoimpl.jdbc;

import com.ascending.demo.api.dao.SuppliersDao;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SuppliersDaoJDBCTest {

    private static final Logger logger = LoggerFactory.getLogger(SuppliersDaoJDBCTest.class);

    private SuppliersDao suppliersDao;

    @BeforeAll
    public static void setupAll() {

    }
    @AfterAll
    public static void teardownAll() {
        suppliersDao = null;
    }
    @BeforeEach
    public void setupEach() {
        majorDao = new MajorDaoJDBCImpl();
    }
    @AfterEach
    public void teardownEach() {
        majorDao = null;
    }

    @Test
    public void testFindAllMajors() {
        List<Major> majorList = majorDao.getMajors();
        assertNotNull(majorList, "the original major list should not be null");
        assertEquals(7,majorList.size(), "the original major list size should be 7");

}
