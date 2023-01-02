package com.bazi.fullystocked.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class orderedarticles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oarticleid;
    @Min(0)
    private int price;
    @Column(nullable = false)
    @NotNull(message = "Ordered Article must have quantity")
    @Min(0)
    private int quantity;
    @Column(nullable = false)
    @NotNull(message = "Ordered Article must have status")
    @NotEmpty(message = "Ordered Article must have status")
    private String articlestatus;
    @ManyToOne
    @JoinColumn(name = "orderid")
    private orders order;
    @ManyToOne
    @JoinColumn(name = "locationid")
    private locations location;
    @ManyToOne
    @JoinColumn(name = "articleid")
    private articles article;

    public orderedarticles(int price, int quantity, String articlestatus, orders order, locations location, articles article) {
        this.price = price;
        this.quantity = quantity;
        this.articlestatus = articlestatus;
        this.order = order;
        this.location = location;
        this.article = article;
    }
}
