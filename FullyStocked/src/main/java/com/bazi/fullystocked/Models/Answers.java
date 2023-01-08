package com.bazi.fullystocked.Models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
public class Answers {
    @EmbeddedId
    private AnswerId answerId;
    private LocalDateTime datecreated;
    @NotNull(message = "The answer must have content")
    @NotEmpty(message = "The location must have content")
    @Column(nullable = false)
    private String answertext;

    public Answers(Questions question, Integer answerCount, String answertext) {
        this.datecreated = LocalDateTime.now();
        this.answertext = answertext;
        this.answerId=new AnswerId();
        this.answerId.setAnswerid(answerCount);
        this.answerId.setQuestion(question);
    }
}
