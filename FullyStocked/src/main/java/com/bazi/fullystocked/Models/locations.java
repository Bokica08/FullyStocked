package com.bazi.fullystocked.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class locations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationid;
    @Column(nullable = false)
    @NotNull(message = "Location must have name")
    @NotEmpty(message = "Location must have name")
    private String locationname;
    @Column(nullable = false)
    @NotNull(message = "Location must have phone")
    @NotEmpty(message = "Location must have phone")
    private String phone;
    @Column(nullable = false)
    @NotNull(message = "Location must have street")
    @NotEmpty(message = "Location must have street")
    private String street;
    @Column(nullable = false)
    @NotNull(message = "Location must have street number")
    private Integer streetnumber;
    @Column(nullable = false)
    @NotNull(message = "Location must have city")
    @NotEmpty(message = "Location must have city")
    private String city;

    public locations(String locationname, String phone, String street, Integer streetnumber, String city) {
        this.locationname = locationname;
        this.phone = phone;
        this.street = street;
        this.streetnumber = streetnumber;
        this.city = city;
    }
}
