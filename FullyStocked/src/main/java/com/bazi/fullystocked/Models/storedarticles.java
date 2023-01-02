package com.bazi.fullystocked.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class storedarticles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sarticleid;
    @Column(nullable = false)
    @NotNull(message = "Stored Article must have quantity")
    @NotEmpty(message = "Stored Article must have quantity")
    @Min(0)
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "locationid")
    private locations locations;
    @ManyToOne
    @JoinColumn(name = "articleid")
    private articles article;
    @ManyToMany
    @JoinTable(name = "question_availability_for_storedarticle",
            joinColumns = @JoinColumn(name = "sarticleid"),
            inverseJoinColumns = @JoinColumn(name = "questionid")
    )
    private List<questions> questionsList=new ArrayList<>();

    public storedarticles(int quantity, locations locations, articles article) {
        this.quantity = quantity;
        this.locations = locations;
        this.article = article;
    }
}
