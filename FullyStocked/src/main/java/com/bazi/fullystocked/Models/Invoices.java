package com.bazi.fullystocked.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
public class Invoices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invoiceid;
    private String customername;
    private String customerphone;
    private String street;
    private int streetnumber;
    private String city;
    @Column(nullable = false)
    @NotNull(message = "Invoice must have creation date")
    private LocalDateTime datecreate;
    @ManyToOne
    @JoinColumn(name = "workeruserid")
    private Workers worker;
    @OneToMany(mappedBy = "invoice")
    private List<InvoicedArticles>articlesList=new ArrayList<>();

    public Invoices(Workers worker) {

        this.datecreate = LocalDateTime.now();
        this.worker = worker;
    }

    public Invoices(String customername, String customerphone, String street, int streetnumber, String city, Workers worker) {
        this.customername = customername;
        this.customerphone = customerphone;
        this.street = street;
        this.streetnumber = streetnumber;
        this.city = city;
        this.worker = worker;
        this.datecreate=LocalDateTime.now();
    }
}
