package com.bazi.fullystocked.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class AnswerId implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerid;
    @ManyToOne
    @JoinColumn(name="questionid")
    private Questions question;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerId answerId = (AnswerId) o;
        return answerid.equals(answerId.answerid) && question.equals(answerId.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerid, question);
    }


}
