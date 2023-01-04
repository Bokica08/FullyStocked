package com.bazi.fullystocked.Services.Implementations;

import com.bazi.fullystocked.Models.Articles;
import com.bazi.fullystocked.Models.Categories;
import com.bazi.fullystocked.Models.Exceptions.InvalidArgumentsException;
import com.bazi.fullystocked.Repositories.ArticlesRepository;
import com.bazi.fullystocked.Repositories.CategoriesRepository;
import com.bazi.fullystocked.Services.ArticlesService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ArticlesServiceImpl implements ArticlesService {
    private final ArticlesRepository articlesRepository;
    private final CategoriesRepository categoriesRepository;

    public ArticlesServiceImpl(ArticlesRepository articlesRepository, CategoriesRepository categoriesRepository) {
        this.articlesRepository = articlesRepository;
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public Optional<Articles> create(String description, String articlename, int maxquantityperlocation) {
        if(description==null || description.isEmpty() || articlename==null || articlename.isEmpty() || maxquantityperlocation<=0)
        {
            throw new InvalidArgumentsException();
        }
        return Optional.of(articlesRepository.save(new Articles(description, articlename, maxquantityperlocation)));
    }

    @Override
    public Optional<Articles> create(String description, String articlename, String imageurl, int maxquantityperlocation) {
        if(description==null || description.isEmpty() || articlename==null || articlename.isEmpty() || maxquantityperlocation<=0)
        {
            throw new InvalidArgumentsException();
        }
        return Optional.of(articlesRepository.save(new Articles(description, articlename, imageurl, maxquantityperlocation)));
    }

    @Override
    @Transactional
    public Optional<Articles> addToCategory(Integer articleId, Integer categoryId) {
        Categories category=categoriesRepository.findById(categoryId).orElseThrow(InvalidArgumentsException::new);
        Articles articles=articlesRepository.findById(articleId).orElseThrow(InvalidArgumentsException::new);
        articles.getCategoryList().add(category);
        articlesRepository.save(articles);
        return Optional.of(articles);
    }
}
