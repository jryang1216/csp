package com.ascending.demo.api.dao.jdbc;

import java.util.List;

public interface DepartmentDetailDao {
    List<DepartmentDetail> getDepartmentDetails();
    DepartmentDetail getDepartmentDetailById(Long id);
    DepartmentDetail save(DepartmentDetail departmentDetail);
    boolean delete(DepartmentDetail departmentDetail);
}
