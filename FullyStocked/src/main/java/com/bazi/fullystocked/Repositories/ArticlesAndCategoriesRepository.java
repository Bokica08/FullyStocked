package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.SqlViews.ArticlesAndCategoriesId;
import com.bazi.fullystocked.Models.SqlViews.ArticlesAndCategoriesReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ArticlesAndCategoriesRepository extends JpaRepository<ArticlesAndCategoriesReport, ArticlesAndCategoriesId> {
    List<ArticlesAndCategoriesReport> findAllById_Articleid(Integer id);
    List<ArticlesAndCategoriesReport> findAllById_Categoryid(Integer id);
}
