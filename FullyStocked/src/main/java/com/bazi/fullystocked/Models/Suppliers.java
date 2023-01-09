package com.bazi.fullystocked.Models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Suppliers extends User{
    @Column(nullable = false)
    @NotNull(message = "Supplier must have supplierInfo")
    @NotEmpty(message = "Supplier must have supplierInfo")
    private String supplierinfo;
    @Column(nullable = false)
    @NotNull(message = "Supplier must have phone")
    @NotEmpty(message = "Supplier must have phone")
    private String phone;
    @Column(nullable = false)
    @NotNull(message = "Supplier must have street")
    @NotEmpty(message = "Supplier must have street")
    private String street;
    @Column(nullable = false)
    @NotNull(message = "Supplier must have street number")
    private int streetnumber;
    @Column(nullable = false)
    @NotNull(message = "Supplier must have street city")
    @NotEmpty(message = "Supplier must have street city")
    private String city;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "supplier_supplies_category",
            joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "categoryid")
    )
    private List<Categories> categoryList=new ArrayList<>();

    public Suppliers(String firstname, String lastname, String username, String email, String password, String supplierinfo, String phone, String street, int sttreetnumber, String city) {
        super(firstname, lastname, username, email, password);
        this.supplierinfo = supplierinfo;
        this.phone = phone;
        this.street = street;
        this.streetnumber = sttreetnumber;
        this.city = city;
    }
}
