package com.bazi.fullystocked.Models.SqlViews;

import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "articles_report")
public class ArticlesReport {
    @Id
    private Integer articleid;
    private String articlename;
    private String description;
    private String imageurl;
    private int quantity;
    private String locationname;
    private Integer locationid;
}
