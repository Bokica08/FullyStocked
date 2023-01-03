package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.Articles;
import com.bazi.fullystocked.Models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ArticlesRepository extends JpaRepository<Articles, Integer> {
    List<Articles> findAllByArticlenameContainingIgnoreCase(String name);
    List<Articles> findAllByCategoryListContaining(Categories category);
}
