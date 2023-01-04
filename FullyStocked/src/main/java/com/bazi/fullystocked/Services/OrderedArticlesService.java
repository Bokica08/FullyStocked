package com.bazi.fullystocked.Services;

import com.bazi.fullystocked.Models.Enumerations.ArticleStatus;
import com.bazi.fullystocked.Models.OrderedArticles;
import com.bazi.fullystocked.Models.SqlViews.OrderedArticlesReport;
import java.util.*;

public interface OrderedArticlesService {
    List<OrderedArticlesReport> findAllByOrder(Integer orderId);
    Optional<OrderedArticles> update(Integer oarticleid, int price, int quantity);
    List<OrderedArticlesReport> findByStatusAtLocation(ArticleStatus status, Integer locationid);
    Optional<OrderedArticlesReport> findById(Integer oarticleid);
}
