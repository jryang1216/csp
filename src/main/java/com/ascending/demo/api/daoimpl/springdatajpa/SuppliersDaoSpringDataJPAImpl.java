package com.ascending.demo.api.daoimpl.springdatajpa;

import com.ascending.demo.api.dao.SuppliersDao;
import com.ascending.demo.api.repository.SuppliersRepository;
import com.ascending.demo.api.entity.Suppliers;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("suppliersDaoSpringDataJPAImpl")
public class SuppliersDaoSpringDataJPAImpl implements SuppliersDao {
    private Logger logger = LoggerFactory.getLogger(SuppliersDaoSpringDataJPAImpl.class);

    @Autowired
    private SuppliersRepository suppliersRepository;

    @Override
    @Transactional
    public Suppliers save(Suppliers suppliers) {
        Suppliers savedSuppliers = suppliersRepository.save(suppliers);
        return savedSuppliers;
    }

    @Override
    public Suppliers update(Suppliers suppliers) {
        Suppliers updatedSuppliers = suppliersRepository.save(suppliers);
        return updatedSuppliers;
    }

    @Override
    public boolean deleteById(Long id) {
        boolean successFlag = false;
        try {
            suppliersRepository.deleteById(id);
            successFlag = true;
        } catch (IllegalArgumentException iae) {
            logger.error("caught IllegalArgumentException when trying deleteById with productId = {}, error = {}", id, iae.getMessage());
        } catch (OptimisticEntityLockException olfe) {
            logger.error("caught OptimisticEntityLockException when trying deleteById with productId = {}, error = {}", id, olfe.getMessage());
        }
        return successFlag;
    }

    @Override
    public boolean delete(Suppliers suppliers) {
        boolean successFlag = false;
        try {
            suppliersRepository.delete(suppliers);
            successFlag = true;
        } catch (IllegalArgumentException iae) {
            logger.error("caught IllegalArgumentException when trying delete supplier with error = {}", iae.getMessage());
        } catch (OptimisticEntityLockException olfe) {
            logger.error("caught OptimisticEntityLockException when trying delete supplier with error = {}", olfe.getMessage());
        }
        return successFlag;
    }


    @Override
    public List<Suppliers> getSuppliers() {
        return suppliersRepository.findAll();
    }

    @Override
    public Suppliers getSupplierById(Long id) {
        Suppliers suppliers = null;
//        Optional<Suppliers> suppliersOptional = Optional.ofNullable(suppliersRepository.findById(id));
//        if(suppliersOptional.isPresent())
//            suppliers = suppliersOptional.get();
        return suppliers;
    }

    @Override
    public Suppliers getSuppliersAndUsersBySupplierId(Long supplierId) {
        return null;
    }


}
