package com.bazi.fullystocked.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="storedarticles")
public class StoredArticles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sarticleid;
    @Column(nullable = false)
    @NotNull(message = "Stored Article must have quantity")
    @Min(0)
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "locationid")
    private Locations locations;
    @ManyToOne
    @JoinColumn(name = "articleid")
    private Articles article;
    @ManyToMany
    @JoinTable(name = "question_availability_for_storedarticle",
            joinColumns = @JoinColumn(name = "sarticleid"),
            inverseJoinColumns = @JoinColumn(name = "questionid")
    )
    private List<Questions> questionsList=new ArrayList<>();

    public StoredArticles(int quantity, Locations locations, Articles article) {
        this.quantity = quantity;
        this.locations = locations;
        this.article = article;
    }
}
