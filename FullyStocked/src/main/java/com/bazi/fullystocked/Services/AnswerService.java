package com.bazi.fullystocked.Services;

import com.bazi.fullystocked.Models.Answers;

import java.util.*;

public interface AnswerService {
    List<Answers> findAllAnswersToQuestion(Integer questionid);
    Optional<Answers> create(Integer questionid, String text);

}
