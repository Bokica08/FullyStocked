package com.bazi.fullystocked.Models.SqlViews;

import lombok.Getter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Getter
@Table(name = "articles_at_location")
public class ArticlesAtLocationReport {
    @Id
    private Integer sarticleid;
    private Integer locationid;
    private String locationname;
    private Integer articleid;
    private String description;
    private String articlename;
    private String imageurl;
    private int maxquantityperlocation;
    private int quantity;

}
