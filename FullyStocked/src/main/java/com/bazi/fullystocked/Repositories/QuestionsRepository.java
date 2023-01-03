package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.Managers;
import com.bazi.fullystocked.Models.Questions;
import com.bazi.fullystocked.Models.StoredArticles;
import com.bazi.fullystocked.Models.Workers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Integer> {
    List<Questions> findAllByManager(Managers manager);
    List<Questions> findAllByWorker(Workers worker);
    List<Questions> findAllByStoredarticlesListContaining(StoredArticles article);
}
