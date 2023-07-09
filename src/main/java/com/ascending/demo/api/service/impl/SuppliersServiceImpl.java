package com.ascending.demo.api.service.impl;

import com.ascending.demo.api.dao.SuppliersDao;
import com.ascending.demo.api.dao.UsersDao;
import com.ascending.demo.api.dto.SuppliersDto;
import com.ascending.demo.api.entity.Suppliers;
import com.ascending.demo.api.exception.ItemNotFoundException;
import com.ascending.demo.api.service.SuppliersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SuppliersServiceImpl implements SuppliersService {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    @Qualifier("suppliersDaoSpringDataJPAImpl")
    private SuppliersDao suppliersDao;

    @Autowired
    @Qualifier("usersDaoSpringDataJPAImpl")
    private UsersDao usersDao;


    @Override
    public SuppliersDto save(SuppliersDto suppliersDto) {
        Suppliers suppliers = suppliersDto.convertSuppliersDtoToSuppliers();
        Suppliers savedSupplier = suppliersDao.save(suppliers);
        SuppliersDto savedSupplierDto = savedSupplier.convertSuppliersToSuppliersDto();
        return savedSupplierDto;
    }

    @Override
    public SuppliersDto update(SuppliersDto suppliersDto) {
        Suppliers suppliers = suppliersDto.convertSuppliersDtoToSuppliers();
        Suppliers updatedSupplier = suppliersDao.update(suppliers);
        SuppliersDto updatedSupplierDto = updatedSupplier.convertSuppliersToSuppliersDto();
        return updatedSupplierDto;
    }

    @Override
    public boolean deleteById(Long id) {
        boolean deleteResult = suppliersDao.deleteById(id);
        logger.debug(" === within method SuppliersServiceImpl.deleteById(...), majorId = {}, deleteResult = {}", id, deleteResult);
        return deleteResult;
    }

    @Override
    public boolean delete(SuppliersDto suppliersDto) {
        Suppliers suppliers = suppliersDto.convertSuppliersDtoToSuppliers();
        boolean deleteResult = suppliersDao.delete(suppliers);
        return deleteResult;
    }

    @Override
    public List<SuppliersDto> getSuppliers() {
        List<Suppliers> suppliersList = suppliersDao.getSuppliers();
        List<SuppliersDto> suppliersDtoList = getSuppliersDtoListFromSuppliersList(suppliersList);
        usersDao.getAllUsers();
        return suppliersDtoList;
    }

    @Override
    public SuppliersDto getSupplierById(Long id) {
        SuppliersDto suppliersDto = null;
        Suppliers suppliers = suppliersDao.getSupplierById(id);
        if (suppliers != null) {
            suppliersDto = suppliers.convertSuppliersToSuppliersDto();
        } else {
            throw new ItemNotFoundException("Could not find Supplier with id = " + id);
        }
        return suppliersDto;
    }




    private List<SuppliersDto> getSuppliersDtoListFromSuppliersList(List<Suppliers> suppliersList) {
        List<SuppliersDto> suppliersDtoList = new ArrayList<>();
        for (Suppliers suppliers : suppliersList) {
            SuppliersDto suppliersDto = suppliers.convertSuppliersToSuppliersDto();
            suppliersDtoList.add(suppliersDto);
        }
        return suppliersDtoList;
    }

}


