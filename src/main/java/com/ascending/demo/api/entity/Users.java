package com.ascending.demo.api.entity;
import com.ascending.demo.api.dto.ProductDto;
import com.ascending.demo.api.dto.UsersDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "users")

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

//    @Column (name = "supplier_id",insertable = false, updatable = false)
//    private Long supplierId;

    @Column (name = "login_name")
    private String loginName;

    @Column (name = "password")
    private String password;

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Column (name = "email")
    private String email;

    @Column (name = "registered_date")
    private LocalDate registeredDate;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "supplier_id")
    private Suppliers suppliers;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Suppliers getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Suppliers suppliers) {
        this.suppliers = suppliers;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", registeredDate=" + registeredDate +
                ", suppliers=" + suppliers +
                '}';
    }
    public UsersDto convertUsersToUsersDto() {
        UsersDto usersDto = new UsersDto();
        usersDto.setId(usersDto.getId());
        usersDto.setLoginName(usersDto.getLoginName());
        usersDto.setLastName(getLastName());
        usersDto.setFirstName(getFirstName());
        usersDto.setEmail(getEmail());
        usersDto.setRegisteredDate(getRegisteredDate());
        return usersDto;
    }
}
