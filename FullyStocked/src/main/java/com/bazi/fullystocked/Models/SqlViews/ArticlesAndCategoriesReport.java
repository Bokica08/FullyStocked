package com.bazi.fullystocked.Models.SqlViews;

import org.hibernate.annotations.Immutable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "articles_and_cats_report")
public class ArticlesAndCategoriesReport {
    @EmbeddedId
    private ArticlesAndCategoriesId id;
    private String categoryname;
    private String articlename;
}
