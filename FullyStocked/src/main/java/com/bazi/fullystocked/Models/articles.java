package com.bazi.fullystocked.Models;

import jdk.jfr.Category;
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
public class articles {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long articleid;
        @Column(nullable = false)
        @NotNull(message = "Article must have description")
        @NotEmpty(message = "Article must have description")
        private String description;
    @Column(nullable = false)
    @NotNull(message = "Article must have name")
    @NotEmpty(message = "Article must have name")
    private String articlename;
    private String imageurl;
    @Column(nullable = false)
    @NotNull(message = "Article must have max quantity")
    @NotEmpty(message = "Article must have max quantity")
    private int maxquantityperlocation;
    @ManyToMany
    @JoinTable(name = "article_belongs_to_category",
            joinColumns = @JoinColumn(name = "articleid"),
            inverseJoinColumns = @JoinColumn(name = "categoryid")
    )
    private List<categories> categoryList=new ArrayList<>();

    public articles(String description, String articlename, String imageurl, int maxquantityperlocation) {
        this.description = description;
        this.articlename = articlename;
        this.imageurl = imageurl;
        this.maxquantityperlocation = maxquantityperlocation;
    }
}
