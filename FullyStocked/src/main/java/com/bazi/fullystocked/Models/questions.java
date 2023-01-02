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
public class questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionid;
    @Column(nullable = false)
    @NotNull(message = "Question must have content")
    @NotEmpty(message = "Question must have content")
    private String questiontext;
    @Column(nullable = false)
    @NotNull(message = "Question must have creation date")
    @NotEmpty(message = "Question must have creation date")
    private LocalDateTime datecreated;
    @ManyToOne
    @JoinColumn(name = "workeruserid")
    private workers worker;
    @ManyToOne
    @JoinColumn(name = "manageruserid")
    private managers manager;
    @ManyToMany(mappedBy = "questionsList")
    private List<storedarticles> storedarticlesList=new ArrayList<>();


    public questions(String questiontext, workers worker, managers manager) {
        this.questiontext = questiontext;
        this.datecreated = LocalDateTime.now();
        this.worker = worker;
        this.manager = manager;
    }
}
