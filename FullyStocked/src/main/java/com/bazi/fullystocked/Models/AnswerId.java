package com.bazi.fullystocked.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
@Getter
@Setter
public class AnswerId implements Serializable {
    private Long answerid;
    private Long questionid;


}
