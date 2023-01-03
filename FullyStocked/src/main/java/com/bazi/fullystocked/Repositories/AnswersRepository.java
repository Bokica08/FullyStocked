package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.AnswerId;
import com.bazi.fullystocked.Models.Answers;
import com.bazi.fullystocked.Models.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface AnswersRepository extends JpaRepository<Answers, AnswerId> {
    List<Answers> findAnswersByAnswerId_Question(Questions q);
}
