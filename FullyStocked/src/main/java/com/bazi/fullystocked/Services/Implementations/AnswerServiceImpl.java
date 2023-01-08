package com.bazi.fullystocked.Services.Implementations;

import com.bazi.fullystocked.Models.Answers;
import com.bazi.fullystocked.Models.Exceptions.InvalidArgumentsException;
import com.bazi.fullystocked.Models.Questions;
import com.bazi.fullystocked.Repositories.AnswersRepository;
import com.bazi.fullystocked.Repositories.QuestionsRepository;
import com.bazi.fullystocked.Services.AnswerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswersRepository answersRepository;
    private final QuestionsRepository questionsRepository;

    public AnswerServiceImpl(AnswersRepository answersRepository, QuestionsRepository questionsRepository) {
        this.answersRepository = answersRepository;
        this.questionsRepository = questionsRepository;
    }

    @Override
    public List<Answers> findAllAnswersToQuestion(Integer questionid) {
        Questions question = questionsRepository.findById(questionid).orElseThrow(InvalidArgumentsException::new);
        return answersRepository.findAnswersByAnswerId_Question(question);
    }

    @Override
    public Optional<Answers> create(Integer questionid, String text) {
        Questions question = questionsRepository.findById(questionid).orElseThrow(InvalidArgumentsException::new);
        if(text==null || text.isEmpty())
        {
            throw new InvalidArgumentsException();
        }
        return Optional.of(answersRepository.save(new Answers(question, answersRepository.countAnswersByAnswerId_Question(question) +1, text)));
    }
}
