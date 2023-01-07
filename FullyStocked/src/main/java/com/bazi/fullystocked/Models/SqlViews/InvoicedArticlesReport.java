package com.bazi.fullystocked.Models.SqlViews;

import com.bazi.fullystocked.Models.Enumerations.ArticleStatus;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Getter
@Table(name = "invoiced_articles_report")
public class InvoicedArticlesReport {
    @Id
    private Integer iarticleid;
    private Integer articleid;
    private String description;
    private String articlename;
    private String imageurl;
    private int maxquantityperlocation;
    private int quantity;
    private int price;
    private int invoiceid;
}
