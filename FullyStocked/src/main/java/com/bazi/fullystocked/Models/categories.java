package com.bazi.fullystocked.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryid;
    @Column(nullable = false)
    @NotNull(message = "Category must have name")
    @NotEmpty(message = "Category must have name")
    private String categoryname;
    @Column(nullable = false)
    @NotNull(message = "Category must have description")
    @NotEmpty(message = "Category must have description")
    private String description;
    @ManyToMany(mappedBy = "categoryList")
    private List<articles> articlesList=new ArrayList<>();
    @ManyToMany(mappedBy = "categoryList2")
    private List<suppliers> suppliersList=new ArrayList<>();


    public categories(String categoryname, String description) {
        this.categoryname = categoryname;
        this.description = description;
    }
}
