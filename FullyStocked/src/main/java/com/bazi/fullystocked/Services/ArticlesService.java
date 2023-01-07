package com.bazi.fullystocked.Services;

import com.bazi.fullystocked.Models.Articles;
import com.bazi.fullystocked.Models.Categories;
import com.bazi.fullystocked.Models.SqlViews.ArticlesAtLocationReport;
import com.bazi.fullystocked.Models.SqlViews.SupplierSuppliesArticleReport;

import java.util.*;

public interface ArticlesService {
    Optional<Articles> create(String description, String articlename, int maxquantityperlocation);
    Optional<Articles> create(String description, String articlename, String imageurl, int maxquantityperlocation);
    Optional<Articles> addToCategory(Integer articleId, Integer categoryId);
    List<Categories> findAllCategoriesByArticle(Integer articleId);
    List<SupplierSuppliesArticleReport> findAllBySupplier(Integer id);
    List<ArticlesAtLocationReport> findAvailabilityAtAllLocations(Integer id);

}
