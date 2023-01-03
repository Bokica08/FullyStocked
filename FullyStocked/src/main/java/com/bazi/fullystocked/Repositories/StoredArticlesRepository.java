package com.bazi.fullystocked.Repositories;
import com.bazi.fullystocked.Models.Articles;
import com.bazi.fullystocked.Models.Locations;
import com.bazi.fullystocked.Models.StoredArticles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface StoredArticlesRepository extends JpaRepository<StoredArticles, Integer> {
    List<StoredArticles> findAllByLocations(Locations location);
    List<StoredArticles> findAllByArticle(Articles article);
    List<StoredArticles> findAllByArticleAndLocations(Articles article, Locations location);
    List<StoredArticles> findAllByLocationsAndQuantityIsLessThanEqual(Locations location, int quantity);
    List<StoredArticles> findAllByLocationsAndQuantity(Locations location, int quantity);

}
