package com.bazi.fullystocked.Services.Implementations;

import com.bazi.fullystocked.Models.Categories;
import com.bazi.fullystocked.Repositories.CategoriesRepository;
import com.bazi.fullystocked.Services.CategoriesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;

    public CategoriesServiceImpl(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public List<Categories> findAll() {
        return categoriesRepository.findAll();
    }
}
