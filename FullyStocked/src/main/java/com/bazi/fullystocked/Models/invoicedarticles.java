package com.bazi.fullystocked.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class invoicedarticles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iarticleid;
    @Column(nullable = false)
    @NotNull(message = "Invoiced Article must have price")
    @Min(0)
    private int price;
    @Column(nullable = false)
    @NotNull(message = "Invoiced Article must have quantity")
    @Min(0)
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "invoiceid")
    private invoices invoice;
    @ManyToOne
    @JoinColumn(name = "articleid")
    private articles article;

    public invoicedarticles(int price, int quantity, invoices invoice, articles article) {
        this.price = price;
        this.quantity = quantity;
        this.invoice = invoice;
        this.article = article;
    }
}
