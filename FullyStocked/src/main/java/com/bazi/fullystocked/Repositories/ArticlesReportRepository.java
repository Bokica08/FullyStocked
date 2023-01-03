package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.SqlViews.ArticlesReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ArticlesReportRepository extends JpaRepository<ArticlesReport, Integer> {
    Optional<ArticlesReport> findByArticleid(Integer id);
    List<ArticlesReport> findAllByLocationid(Integer id);
}
