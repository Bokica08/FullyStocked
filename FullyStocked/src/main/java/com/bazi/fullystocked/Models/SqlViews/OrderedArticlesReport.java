package com.bazi.fullystocked.Models.SqlViews;

import com.bazi.fullystocked.Models.Enumerations.ArticleStatus;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Table(name = "ordered_articles_report")
public class OrderedArticlesReport {
    @Id
    private Integer oarticleid;
    private Integer articleid;
    private String description;
    private String articlename;
    private String imageurl;
    private int maxquantityperlocation;
    private int quantity;
    private Integer locationid;
    private int price;
    @Enumerated(EnumType.STRING)
    private ArticleStatus articlestatus;
    private int orderid;
}
