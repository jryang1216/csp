package com.ascending.demo.api.dto;

import com.ascending.demo.api.entity.Product;
import com.ascending.demo.api.entity.Suppliers;
import com.ascending.demo.api.entity.Users;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.HashSet;

public class UsersDto {
    private long id;
    private Long supplierId;
    private String loginName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate registeredDate;
    private SuppliersDto suppliersDto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }

    public SuppliersDto getSuppliersSet() {
        return suppliersDto;
    }

    public void setSuppliersSet(SuppliersDto suppliersDto) {
        this.suppliersDto = suppliersDto;
    }

    @Override
    public String toString() {
        return "UsersDto{" +
                "id=" + id +
                ", supplierId=" + supplierId +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", registeredDate=" + registeredDate +
                ", suppliersDto=" + suppliersDto +
                '}';
    }
    public Users convertUsersDtoToUsers() {
        Users users = new Users();
        users.setId(getId());
        users.setEmail(getEmail());
        users.setFirstName(getFirstName());
        users.setLastName(getLastName());
        users.setLoginName(getLoginName());
        users.setRegisteredDate(getRegisteredDate());
        return users;
    }

}
