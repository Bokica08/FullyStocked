package com.bazi.fullystocked.Models.SqlViews;

import lombok.Getter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Getter
@Table(name = "suppliers_report")
public class SuppliersReport {
    @Id
    Integer userid;
    String username;
    String firstname;
    String lastname;
    String street;
    int streetnumber;
    String city;
    String phone;
    String supplierinfo;
}
