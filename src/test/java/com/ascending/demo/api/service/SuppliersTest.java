package com.ascending.demo.api.service;

import com.ascending.demo.api.dao.SuppliersDao;
import com.ascending.demo.api.dao.UsersDao;
import com.ascending.demo.api.dto.SuppliersDto;
import com.ascending.demo.api.entity.Suppliers;
import com.ascending.demo.api.entity.Users;
import com.ascending.demo.api.service.impl.SuppliersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SuppliersTest {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Mock
    private SuppliersDao mockSuppliersDao;

    @Mock
    private UsersDao mockUsersDao;

    @InjectMocks
    private SuppliersServiceImpl suppliersServiceImpl;

    @BeforeEach
    public void setupEach() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testSaveSuppliersDto() {
        Suppliers mockSuppliers = mock(Suppliers.class);
        SuppliersDto mockSuppliersDto = mock(SuppliersDto.class);

        when(mockSuppliersDto.convertSuppliersDtoToSuppliers()).thenReturn(mockSuppliers);
        when(mockSuppliers.convertSuppliersToSuppliersDto()).thenReturn(mockSuppliersDto);
        when(mockSuppliersDao.save(mockSuppliers)).thenReturn(mockSuppliers);

        SuppliersDto suppliersDto = suppliersServiceImpl.save(mockSuppliersDto);
        verify(mockSuppliersDao, times(1)).save(mockSuppliers);
        verify(mockSuppliersDto, times(1)).convertSuppliersDtoToSuppliers();
        verify(mockSuppliers, times(1)).convertSuppliersToSuppliersDto();
    }



    @Test
    public void testDeleteSuppliersById() {
        when(mockSuppliersDao.deleteById(anyLong())).thenReturn(false);
        boolean deleteResult = suppliersServiceImpl.deleteById(anyLong());
        assertFalse(deleteResult);
        verify(mockSuppliersDao, times(1)).deleteById(anyLong());
    }

    @Test
    public void testGetAllSuppliers_usingSpyList() {
//        List<Suppliers> suppliersList = new ArrayList<>();
        List<Suppliers> spySupplierList = spy(ArrayList.class);
        Suppliers mockSupplier = mock(Suppliers.class);

        spySupplierList.add(mockSupplier);
        spySupplierList.add(mockSupplier);
        spySupplierList.add(mockSupplier);

        SuppliersDto mockSuppliersDto = mock(SuppliersDto.class);

        when(mockSupplier.convertSuppliersToSuppliersDto()).thenReturn(mockSuppliersDto);
        when(mockSuppliersDao.getSuppliers()).thenReturn(spySupplierList);

        List<Users> spyUsersList = spy(ArrayList.class);
        when(mockUsersDao.getAllUsers()).thenReturn(spyUsersList);

        List<SuppliersDto> supplierDtoList = suppliersServiceImpl.getSuppliers();

        assertEquals(3, supplierDtoList.size());

        verify(mockSuppliersDao, times(1)).getSuppliers();
        verify(mockSupplier, times(3)).convertSuppliersToSuppliersDto();

        verify(mockUsersDao, times(1)).getAllUsers();
    }
    @Test
    public void testGetAllSuppliers_usingMockList() {
        Suppliers mockSupplier = mock(Suppliers.class);
        SuppliersDto mockSuppliersDto = mock(SuppliersDto.class);
        List<Suppliers> mockSupplierList = mock(ArrayList.class);

        Iterator mockIterator = mock(Iterator.class);

        when(mockSuppliersDao.getSuppliers()).thenReturn(mockSupplierList);

        when(mockSupplierList.iterator()).thenReturn(mockIterator);
        when(mockIterator.next()).thenReturn(mockSupplier);
        when(mockIterator.hasNext()).thenReturn(true, true, true, true, false); //执行4次

        when(mockSupplier.convertSuppliersToSuppliersDto()).thenReturn(mockSuppliersDto);

        List<SuppliersDto> supplierDtoList = suppliersServiceImpl.getSuppliers();

        verify(mockSuppliersDao, times(1)).getSuppliers();
        verify(mockSupplier, times(4)).convertSuppliersToSuppliersDto();
    }

}
