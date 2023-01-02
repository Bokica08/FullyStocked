package com.bazi.fullystocked.Models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
public class answers {
    @EmbeddedId
    private AnswerId answerId;
    private LocalDateTime datecreated;
    @NotNull(message = "The answer must have content")
    @NotEmpty(message = "The location must have content")
    @Column(nullable = false)
    private String answertext;

    public answers(String answertext) {
        this.datecreated = LocalDateTime.now();
        this.answertext = answertext;
    }
}
