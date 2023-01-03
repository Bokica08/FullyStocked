package com.bazi.fullystocked.Models;

import com.bazi.fullystocked.Models.Enumerations.ArticleStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@Table(name="orderedarticles")
public class OrderedArticles {
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
    @Enumerated(EnumType.STRING)
    private ArticleStatus articlestatus;
    @ManyToOne
    @JoinColumn(name = "orderid")
    private Orders order;
    @ManyToOne
    @JoinColumn(name = "locationid")
    private Locations location;
    @ManyToOne
    @JoinColumn(name = "articleid")
    private Articles article;

    public OrderedArticles(int quantity, Orders order, Locations location, Articles article) {
        this.quantity = quantity;
        this.articlestatus = ArticleStatus.ORDERED;
        this.order = order;
        this.location = location;
        this.article = article;
    }
}
