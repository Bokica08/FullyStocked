package com.bazi.fullystocked.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class invoices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceid;
    @Column(nullable = false)
    @NotNull(message = "Invoice must have customer name")
    @NotEmpty(message = "Invoice must have customer name")
    private String customername;
    @Column(nullable = false)
    @NotNull(message = "Invoice must have customer phone")
    @NotEmpty(message = "Invoice must have customer phone")
    private String customerphone;
    @Column(nullable = false)
    @NotNull(message = "Invoice must have customer street")
    @NotEmpty(message = "Invoice must have customer street")
    private String street;
    @Column(nullable = false)
    @NotNull(message = "Invoice must have customer street number")
    @NotEmpty(message = "Invoice must have customer street number")
    private int streetnumber;
    @Column(nullable = false)
    @NotNull(message = "Invoice must have customer city")
    @NotEmpty(message = "Invoice must have customer city")
    private String city;
    @Column(nullable = false)
    @NotNull(message = "Invoice must have creation date")
    @NotEmpty(message = "Invoice must have creation date")
    private LocalDateTime datecreate;
    @ManyToOne
    @JoinColumn(name = "workeruserid")
    private workers worker;

    public invoices(String customername, String customerphone,
                    String street, int streetnumber, String city, workers worker) {
        this.customername = customername;
        this.customerphone = customerphone;
        this.street = street;
        this.streetnumber = streetnumber;
        this.city = city;
        this.datecreate = LocalDateTime.now();
        this.worker = worker;
    }
}
