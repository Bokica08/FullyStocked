package com.bazi.fullystocked.Services.Implementations;

import com.bazi.fullystocked.Models.Exceptions.ArticleAlreadyInQuestionException;
import com.bazi.fullystocked.Models.Exceptions.InvalidArgumentsException;
import com.bazi.fullystocked.Models.Managers;
import com.bazi.fullystocked.Models.Questions;
import com.bazi.fullystocked.Models.StoredArticles;
import com.bazi.fullystocked.Models.Workers;
import com.bazi.fullystocked.Repositories.ManagersRepository;
import com.bazi.fullystocked.Repositories.QuestionsRepository;
import com.bazi.fullystocked.Repositories.StoredArticlesRepository;
import com.bazi.fullystocked.Repositories.WorkersRepository;
import com.bazi.fullystocked.Services.QuestionsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionsService {
    private final QuestionsRepository questionsRepository;
    private final WorkersRepository workersRepository;
    private final ManagersRepository managersRepository;
    private final StoredArticlesRepository storedArticlesRepository;

    public QuestionServiceImpl(QuestionsRepository questionsRepository, WorkersRepository workersRepository, ManagersRepository managersRepository, StoredArticlesRepository storedArticlesRepository) {
        this.questionsRepository = questionsRepository;
        this.workersRepository = workersRepository;
        this.managersRepository = managersRepository;
        this.storedArticlesRepository = storedArticlesRepository;
    }

    @Override
    public Optional<Questions> create(String text, Integer workerid, Integer managerid) {
        Workers worker=workersRepository.findById(workerid).orElseThrow(InvalidArgumentsException::new);
        Managers manager=managersRepository.findById(managerid).orElseThrow(InvalidArgumentsException::new);
        if(text==null || text.isEmpty())
        {
            throw new InvalidArgumentsException();
        }
        return Optional.of(questionsRepository.save(new Questions(text, worker, manager)));
    }

    @Override
    @Transactional
    public Optional<Questions> addArticle(Integer questionid, Integer sarticleid) {
        Questions question=questionsRepository.findById(questionid).orElseThrow(InvalidArgumentsException::new);
        StoredArticles article=storedArticlesRepository.findById(sarticleid).orElseThrow(InvalidArgumentsException::new);
        if(article.getQuestionsList().stream().anyMatch(questions -> questions.getQuestionid().equals(questionid)))
        {
            throw new ArticleAlreadyInQuestionException();
        }
        article.getQuestionsList().add(question);
        storedArticlesRepository.save(article);
        questionsRepository.save(question);
        return Optional.of(question);
    }

    @Override
    public List<Questions> findAllByManager(Integer managerId) {
        Managers manager=managersRepository.findById(managerId).orElseThrow(InvalidArgumentsException::new);
        return questionsRepository.findAllByManager(manager);
    }

    @Override
    public List<Questions> findAllByWorker(Integer workerId) {
        Workers worker=workersRepository.findById(workerId).orElseThrow(InvalidArgumentsException::new);
        return questionsRepository.findAllByWorker(worker);
    }

    @Override
    public Optional<Questions> findById(Integer questionId) {
        return questionsRepository.findById(questionId);
    }
}
