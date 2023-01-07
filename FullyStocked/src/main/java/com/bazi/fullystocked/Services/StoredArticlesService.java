package com.bazi.fullystocked.Services;


import com.bazi.fullystocked.Models.Categories;
import com.bazi.fullystocked.Models.SqlViews.ArticlesReport;
import com.bazi.fullystocked.Models.SqlViews.SupplierSuppliesArticleReport;
import com.bazi.fullystocked.Models.StoredArticles;

import java.util.*;

public interface StoredArticlesService {
    Optional<StoredArticles> updateFromOrder(Integer oarticleid);
    List<ArticlesReport> findByLocation(Integer locationId);
    Optional<ArticlesReport> findById(Integer id);

}
