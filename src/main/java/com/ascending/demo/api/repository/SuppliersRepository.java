package com.ascending.demo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ascending.demo.api.entity.Suppliers;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliersRepository extends JpaRepository <Suppliers, Long> {
//    Long deleteByNameAndIndustry(String name, String industry);
//
//    @Query("SELECT distinct s FROM Suppliers as s left join fetch s.usersSet as users where s.id = :id")
//    Suppliers findSupplierWithUsersBySupplierId(@Param(value = "id") Long supplierId);
    Suppliers findByName(String name);

    Long deleteByName(String name);

    Long deleteByNameAndDescription(String name, String description);

//    Suppliers findById(Long id);


}
