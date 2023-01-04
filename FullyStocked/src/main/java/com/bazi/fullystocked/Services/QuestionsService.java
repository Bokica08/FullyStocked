package com.bazi.fullystocked.Services;

import com.bazi.fullystocked.Models.Questions;


import java.util.*;

public interface QuestionsService {
    Optional<Questions> create(String text, Integer workerid, Integer managerid);
    Optional<Questions> addArticle(Integer questionid, Integer sarticleid);
    List<Questions> findAllByManager(Integer managerId);
    List<Questions> findAllByWorker(Integer workerId);
    Optional<Questions> findById(Integer questionId);
}
