package com.bazi.fullystocked.Models.SqlViews;

import lombok.Getter;
import org.hibernate.annotations.Immutable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Immutable
@Getter
@Table(name = "supplier_supplies_article")
public class SupplierSuppliesArticleReport {
    @EmbeddedId
    private SupplierSuppliesArticleReportId id;
    private String articlename;
    private String imageurl;
}
