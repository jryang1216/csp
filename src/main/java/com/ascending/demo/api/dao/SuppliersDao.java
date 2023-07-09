package com.ascending.demo.api.dao;

import com.ascending.demo.api.entity.Suppliers;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface SuppliersDao {
    Suppliers save(Suppliers suppliers);
    Suppliers update(Suppliers suppliers);

    boolean deleteById(Long id);
    boolean delete(Suppliers suppliers);

    List<Suppliers> getSuppliers();
    Suppliers getSupplierById (Long id);


    Suppliers getSuppliersAndUsersBySupplierId(Long supplierId);
}
