package com.ascending.demo.api.controller;

import com.ascending.demo.api.dto.SuppliersDto;
import com.ascending.demo.api.service.SuppliersService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.print.attribute.standard.Media;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SuppliersControllerTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SuppliersService mockSuppliersService;

    @InjectMocks
    private SuppliersController suppliersController;

    @BeforeEach
    public void initEach() {
        MockitoAnnotations.openMocks(this);
        //mockMvc = MockMvcBuilders.standaloneSetup(suppliersController).build();
    }
    @Test
    public void testFindAllSuppliers() throws Exception {
        List<SuppliersDto> suppliersDtoList = new ArrayList<>();
        //List<SuppliersDto> suppliersDtoList = spy(ArrayList.class);
        SuppliersDto suppliersDto = createSuppliersDtoByName("aaa");
        Long supplierId = 100L;
        suppliersDto.setId(supplierId);
        suppliersDtoList.add(suppliersDto);
        
        when(mockSuppliersService.getSuppliers()).thenReturn(suppliersDtoList);
        String responseJsonString = JsonStringUtil.convertObjectToJsonString(suppliersDtoList);

        String restUriForFindingAllSuppliers = "/project2023/suppliers";
        mockMvc.perform(get(restUriForFindingAllSuppliers)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(responseJsonString))
                .andExpect(status().isOk());
        verify(mockSuppliersService, times(1)).getSuppliers();
    }

    @Test
    public void testFindSuppliersBySupplierId_happy_path() throws Exception {
        SuppliersDto suppliersDto = createSuppliersDtoByName("aaa");
        Long supplierId = 101L;
        suppliersDto.setId(supplierId);

        when(mockSuppliersService.getSupplierById(anyLong())).thenReturn(suppliersDto);
        String requestJsonString = JsonStringUtil.convertObjectToJsonString(supplierId);
        String responseJsonString = JsonStringUtil.convertObjectToJsonString(supplierId);

        String restUriForGettingSupplierById = "/project2023/suppliers/{id}";
        //Not using RequestBuilder
//        mockMvc.perform(get(restUriForGettingSupplierById, supplierId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestJsonString))
//                .andDo(print())
//                .andExpect(content().json(responseJsonString))
//                .andExpect(status().isOk());
//        verify(mockSuppliersService, times(1)).getSupplierById(anyLong());


        //Using RequestBuilder
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(restUriForGettingSupplierById, supplierId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJsonString);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(content().json(responseJsonString))
                .andExpect(status().isOk());
        verify(mockSuppliersService, times(1)).getSupplierById(anyLong());
    }


    @Test
    public void testCreateSuppliersDto() throws Exception {
        String supplierName = "aaa";
        SuppliersDto inputSuppliersDto = createSuppliersDtoByName("supplierName");

        SuppliersDto savedSuppliersDto = createSuppliersDtoByName(supplierName);
        Long supplierId = 100L;
        savedSuppliersDto.setId(supplierId);

        String requestJsonString = JsonStringUtil.convertObjectToJsonString(inputSuppliersDto);
        String responseJsonString = JsonStringUtil.convertObjectToJsonString(savedSuppliersDto);

        when (mockSuppliersService.save(inputSuppliersDto)).thenReturn(savedSuppliersDto);
        String restUriForCreatingSupplierDto = "/project2023/suppliers";
        mockMvc.perform(post(restUriForCreatingSupplierDto)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJsonString))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(responseJsonString));

        verify(mockSuppliersService, times(1)).save(inputSuppliersDto);

    }

//    @Test
//    public void testUpdateSuppliersDto() throws Exception {
//        String supplierName = "aaa";
//        SuppliersDto inputSuppliersDto = createSuppliersDtoByName("supplierName");
//        Long supplierId = 100L;
//        inputSuppliersDto.setId(supplierId);
//
//        SuppliersDto updatedSuppliersDto = createSuppliersDtoByName(supplierName);
//        updatedSuppliersDto.setId(supplierId);
//        updatedSuppliersDto.setCity(updatedSuppliersDto.getCity() + "_updated");
//
//        String requestJsonString = JsonStringUtil.convertObjectToJsonString(inputSuppliersDto);
//        String responseJsonString = JsonStringUtil.convertObjectToJsonString(updatedSuppliersDto);
//
//        when (mockSuppliersService.update(inputSuppliersDto)).thenReturn(updatedSuppliersDto);
//        String restUriForCreatingSupplierDto = "/project2023/suppliers";
//        mockMvc.perform(put(restUriForCreatingSupplierDto)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestJsonString))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(content().json(responseJsonString))
//                .andExpect(jsonPath("$.id"), is(supplierId.intValue()))
//                .andExpect(jsonPath("$.name", comparesEqualTo(updatedSuppliersDto.getName())));
//
//        verify(mockSuppliersService, times(1)).save(inputSuppliersDto);
//
//    }
    @Test
    public void testDeleteSupplierById_happy_path() throws Exception {

        when(mockSuppliersService.deleteById(anyLong())).thenReturn(true);

        Long supplierId = 100L;
        String requestJsonString = JsonStringUtil.convertObjectToJsonString(supplierId);

        String restUriForDeletingSupplierById = "/project2023/suppliers/{id}";

        mockMvc.perform(delete(restUriForDeletingSupplierById, supplierId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJsonString))
                .andDo(print())
                .andExpect(status().isOk());
        verify(mockSuppliersService, times(1)).deleteById(anyLong());

    }

    @Test
    public void testDeleteSupplierById_failed() throws Exception {

        when(mockSuppliersService.deleteById(anyLong())).thenReturn(false);

        Long supplierId = 100L;
        String requestJsonString = JsonStringUtil.convertObjectToJsonString(supplierId);

        String restUriForDeletingSupplierById = "/project2023/suppliers/{id}";

        mockMvc.perform(delete(restUriForDeletingSupplierById, supplierId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJsonString))
                .andDo(print())
                .andExpect(status().is4xxClientError());
        verify(mockSuppliersService, times(1)).deleteById(anyLong());

    }


    private SuppliersDto createSuppliersDtoByName(String name) {
        SuppliersDto suppliersDto = new SuppliersDto();
        suppliersDto.setName(name);
        return suppliersDto;
    }
}
