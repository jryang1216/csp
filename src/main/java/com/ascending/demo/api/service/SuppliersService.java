package com.ascending.demo.api.service;

import com.ascending.demo.api.dto.SuppliersDto;
import com.ascending.demo.api.entity.Suppliers;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SuppliersService {
    SuppliersDto save(SuppliersDto suppliersDto);
    SuppliersDto update(SuppliersDto suppliersDto);

    boolean deleteById(Long id);
    boolean delete(SuppliersDto suppliersDto);

    List<SuppliersDto> getSuppliers();
    SuppliersDto getSupplierById (Long id);


}
