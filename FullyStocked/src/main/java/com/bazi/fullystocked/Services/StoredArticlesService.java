package com.bazi.fullystocked.Services;


import com.bazi.fullystocked.Models.SqlViews.ArticlesReport;
import com.bazi.fullystocked.Models.SqlViews.SupplierSuppliesArticleReport;
import com.bazi.fullystocked.Models.StoredArticles;

import java.util.*;

public interface StoredArticlesService {
    List<SupplierSuppliesArticleReport> findAllBySupplier(Integer supplierId);
    Optional<StoredArticles> updateFromOrder(Integer oarticleid);
    List<ArticlesReport> findByLocation(Integer locationId);
}
