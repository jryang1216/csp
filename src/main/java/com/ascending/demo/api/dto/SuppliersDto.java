package com.ascending.demo.api.dto;

import com.ascending.demo.api.dao.SuppliersDao;
import com.ascending.demo.api.entity.Product;
import com.ascending.demo.api.entity.Suppliers;
import com.ascending.demo.api.entity.Users;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SuppliersDto {
    private Long id;
    private String name;
    private boolean active;
    private LocalDate createDate;
    private String industry;
    private String streetAddress1;
    private String streetAddress2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private boolean subscribed;

    private Set<UsersDto> usersDtoSet = new HashSet<>();
    private List<ProductDto> productDtoList = new ArrayList<>();

    public Suppliers convertSuppliersDtoToSuppliers() {
        Suppliers suppliers = new Suppliers();
        if (getId() != null)
            suppliers.setId(getId());
            suppliers.setName(getName());
            suppliers.setIndustry(getIndustry());
            suppliers.setCity(getCity());
            Set<Users> usersSet = getUsersSetByUsersDtoSet(this.getUsersSet());
            suppliers.setUsersSet(usersSet);
        return suppliers;
    }

    private Set<Users> getUsersSetByUsersDtoSet(Set<UsersDto> usersDtoSet) {
        Set<Users> userSet = new HashSet<>();
        for (UsersDto usersDto : usersDtoSet) {
            Users users = usersDto.convertUsersDtoToUsers();
            userSet.add(users);
        }
        return userSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Set<UsersDto> getUsersSet() {
        return usersDtoSet;
    }

    public void setUsersSet(Set<UsersDto> usersSet) {
        this.usersDtoSet = usersSet;
    }

    public List<ProductDto> getProductList() {
        return productDtoList;
    }

    public void setProductList(List<ProductDto> productList) {
        this.productDtoList = productList;
    }

    @Override
    public String toString() {
        return "SuppliersDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", createDate=" + createDate +
                ", industry='" + industry + '\'' +
                ", streetAddress1='" + streetAddress1 + '\'' +
                ", streetAddress2='" + streetAddress2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", subscribed=" + subscribed +
                ", usersSet=" + usersDtoSet +
                ", productList=" + productDtoList +
                '}';
    }
}