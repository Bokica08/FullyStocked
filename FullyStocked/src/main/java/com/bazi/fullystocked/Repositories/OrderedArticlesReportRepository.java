package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.Enumerations.ArticleStatus;
import com.bazi.fullystocked.Models.SqlViews.OrderedArticlesReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface OrderedArticlesReportRepository extends JpaRepository<OrderedArticlesReport, Integer> {
    List<OrderedArticlesReport> findAllByOrderid(Integer orderid);
    List<OrderedArticlesReport> findAllByArticlestatus(ArticleStatus status);
    List<OrderedArticlesReport> findAllByArticlestatusAndLocationid(ArticleStatus status, Integer locationid);
}
