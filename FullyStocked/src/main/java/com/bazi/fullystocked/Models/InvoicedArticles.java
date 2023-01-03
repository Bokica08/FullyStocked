package com.bazi.fullystocked.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@Table(name="invoicedarticles")
public class InvoicedArticles {
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
    private Invoices invoice;
    @ManyToOne
    @JoinColumn(name = "articleid")
    private Articles article;

    public InvoicedArticles(int price, int quantity, Invoices invoice, Articles article) {
        this.price = price;
        this.quantity = quantity;
        this.invoice = invoice;
        this.article = article;
    }
}
