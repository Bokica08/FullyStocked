package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.Articles;
import com.bazi.fullystocked.Models.Enumerations.ArticleStatus;
import com.bazi.fullystocked.Models.Locations;
import com.bazi.fullystocked.Models.OrderedArticles;
import com.bazi.fullystocked.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface OrderedArticlesRepository extends JpaRepository<OrderedArticles, Integer> {
    List<OrderedArticles> findAllByArticle(Articles article);
    List<OrderedArticles> findAllByLocation(Locations location);
    List<OrderedArticles> findAllByOrder(Orders order);
    List<OrderedArticles> findAllByOrderAndArticle(Orders order, Articles article);
    List<OrderedArticles> findAllByArticlestatus(ArticleStatus status);
    List<OrderedArticles> findAllByLocationAndArticlestatus(Locations location, ArticleStatus status);
    List<OrderedArticles> findAllByOrderAndArticlestatus(Orders order, ArticleStatus status);
    List<OrderedArticles> findAllByOrderAndPriceIsNull(Orders order);
}
