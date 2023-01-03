package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.Categories;
import com.bazi.fullystocked.Models.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, Integer> {
    List<Suppliers> findAllByCategoryListContains(Categories category);
    List<Suppliers> findAllByCityIgnoreCase(String city);
}
