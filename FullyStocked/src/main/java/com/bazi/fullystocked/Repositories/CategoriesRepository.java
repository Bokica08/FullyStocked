package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
    List<Categories> findAllByCategorynameContainsIgnoreCase(String name);
    List<Categories> findAll();
}
