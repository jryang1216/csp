package com.ascending.demo.api.controller;

import com.ascending.demo.api.dto.SuppliersDto;
import com.ascending.demo.api.exception.ExceptionResponse;
import com.ascending.demo.api.exception.ItemNotFoundException;
import com.ascending.demo.api.service.ProductService;
import com.ascending.demo.api.service.SuppliersService;
import com.ascending.demo.api.service.UsersService;
import com.ascending.demo.api.service.impl.SuppliersServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/project2023")
public class SuppliersController {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private SuppliersService suppliersService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ProductService productService;

//    @GetMapping(value = "/suppliers", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<SuppliersDto>> findAllSuppliers() {
//        List<SuppliersDto> suppliersDtoList = suppliersService.getSuppliers();
//        return new ResponseEntity<>(suppliersDtoList, HttpStatus.OK); //HeepStatus.ACCEPTED
//    }
    @GetMapping(value = "/suppliers", produces = "application/json", headers = {"name = abcd", "id=111"})
    public ResponseEntity<List<SuppliersDto>> findAllSuppliers() {
        List<SuppliersDto> suppliersDtoList = suppliersService.getSuppliers();
        return new ResponseEntity<>(suppliersDtoList, HttpStatus.OK); //HeepStatus.ACCEPTED
    }

    @GetMapping(value = "/suppliers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findSuppliersDtoBySupplierId(@PathVariable("id") Long supplierId) {
        SuppliersDto suppliersDto = null;
        ResponseEntity<Object> responseEntity = null;
        try {
            suppliersDto = suppliersService.getSupplierById(supplierId);
            responseEntity = new ResponseEntity<>(suppliersDto, HttpStatus.OK);
        } catch (ItemNotFoundException e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse("ItemNotFoundException",
                    LocalDateTime.now()); //e.getMessage();
            responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    @GetMapping(value = "/suppliers/param_practice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findSuppliersDtoByRequestParamSupplierId(@RequestParam("id") Long supplierId) {
        SuppliersDto suppliersDto = null;
        ResponseEntity<Object> responseEntity = null;
        try {
            suppliersDto = suppliersService.getSupplierById(supplierId);
            responseEntity = new ResponseEntity<>(suppliersDto, HttpStatus.OK);
        } catch (ItemNotFoundException e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse("ItemNotFoundException",
                    LocalDateTime.now()); //e.getMessage();
            responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    @PostMapping(value = "/majors", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuppliersDto savedSuppliersDto(@RequestBody SuppliersDto suppliersDto) {
        SuppliersDto savedSuppliersDto = suppliersService.save(suppliersDto);
        return savedSuppliersDto;
    }

    @PutMapping(value = "/majors", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuppliersDto updateSuppliersDto(@RequestBody SuppliersDto suppliersDto) {
        SuppliersDto updateSuppliersDto = suppliersService.update(suppliersDto);
        return updateSuppliersDto;
    }

}


