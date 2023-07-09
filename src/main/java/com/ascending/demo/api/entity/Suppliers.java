package com.ascending.demo.api.entity;
import com.ascending.demo.api.dto.SuppliersDto;
import com.ascending.demo.api.dto.UsersDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "suppliers")

public class Suppliers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "active")
    private boolean active;
    @Column(name = "create_date")
    private LocalDate createDate;
    @Column(name = "industry")
    private String industry;
    @Column(name = "street_address_1")
    private String streetAddress1;
    @Column(name = "street_address_2")
    private String streetAddress2;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "zip")
    private String zip;
    @Column(name = "country")
    private String country;
    @Column(name = "subscribed")
    private boolean subscribed;

    @OneToMany(mappedBy = "suppliers", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Users> usersSet;
    public Set<Users> getUsersList() {
        if (usersSet == null)
            usersSet = new HashSet<>();
        return usersSet;
    }
    public void setUsersList(List<Users> usersList) {
        this.usersSet = usersSet;
    }

    //this is a convenient utility kind of method to add a student to this specific major
    public void addUsers(Users users) {
        this.getUsersList().add(users);
        users.setSuppliers(this);
    }

    public void removeUsers(Users users) {
        this.getUsersList().remove(users);
        users.setSuppliers(null);
    }

    @ManyToMany (fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "supplier_product",
            joinColumns = {@JoinColumn(name = "supplier_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private List<Product> productList;

    public List<Product> getProducts() {
        if (productList == null)
            productList = new ArrayList<>();
        return productList;
    }

    public void setProducts(List<Product> productList) {
        this.productList = productList;
    }

    public void addProduct(Product product){
        this.getProducts().add(product);
        product.getSuppliers().add(this);
    }
    public void removeProduct(Product product) {
        this.getProducts().remove(product);
        product.getSuppliers().remove(this);
    }

    public SuppliersDto convertSuppliersToSuppliersDto() {
        SuppliersDto suppliersDto = new SuppliersDto();
        suppliersDto.setId(suppliersDto.getId());
        suppliersDto.setName(getName());
        suppliersDto.setIndustry(getIndustry());
        suppliersDto.setCity(getCity());
        Set<UsersDto> usersDtoSet = getSuppliersDtoListFromSuppliersSet(getUsersSet());
        suppliersDto.setUsersSet(usersDtoSet);
        return suppliersDto;
    }

    private Set<UsersDto> getSuppliersDtoListFromSuppliersSet(Set<Users> usersSet) {
        Set<UsersDto> usersDtoSet = new HashSet<>();
        for (Users users : usersSet) {
            UsersDto usersDto = users.convertUsersToUsersDto();
            usersDtoSet.add(usersDto);
        }
        return usersDtoSet;
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

    public Set<Users> getUsersSet() {
        return usersSet;
    }

    public void setUsersSet(Set<Users> usersSet) {
        this.usersSet = usersSet;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Suppliers{" +
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
                ", usersSet=" + usersSet +
                ", productList=" + productList +
                '}';
    }
}