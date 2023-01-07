package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.SqlViews.ArticlesAtLocationReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ArticlesAtLocationRepository extends JpaRepository<ArticlesAtLocationReport, Integer> {
    List<ArticlesAtLocationReport> findAllByLocationid(Integer id);
    List<ArticlesAtLocationReport> findAllByArticleid(Integer id);
}
