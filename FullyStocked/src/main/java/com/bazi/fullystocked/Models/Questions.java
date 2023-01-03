package com.bazi.fullystocked.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionid;
    @Column(nullable = false)
    @NotNull(message = "Question must have content")
    @NotEmpty(message = "Question must have content")
    private String questiontext;
    @Column(nullable = false)
    @NotNull(message = "Question must have creation date")
    private LocalDateTime datecreated;
    @ManyToOne
    @JoinColumn(name = "workeruserid")
    private Workers worker;
    @ManyToOne
    @JoinColumn(name = "manageruserid")
    private Managers manager;
    @ManyToMany(mappedBy = "questionsList")
    private List<StoredArticles> storedarticlesList=new ArrayList<>();


    public Questions(String questiontext, Workers worker, Managers manager) {
        this.questiontext = questiontext;
        this.datecreated = LocalDateTime.now();
        this.worker = worker;
        this.manager = manager;
    }
}
