package com.bazi.fullystocked.Services.Implementations;

import com.bazi.fullystocked.Models.Articles;
import com.bazi.fullystocked.Models.Categories;
import com.bazi.fullystocked.Models.Exceptions.InvalidArgumentsException;
import com.bazi.fullystocked.Models.SqlViews.ArticlesAtLocationReport;
import com.bazi.fullystocked.Models.SqlViews.SupplierSuppliesArticleReport;
import com.bazi.fullystocked.Repositories.ArticlesAtLocationRepository;
import com.bazi.fullystocked.Repositories.ArticlesRepository;
import com.bazi.fullystocked.Repositories.CategoriesRepository;
import com.bazi.fullystocked.Repositories.SupplierSuppliesArticleRepository;
import com.bazi.fullystocked.Services.ArticlesService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ArticlesServiceImpl implements ArticlesService {
    private final ArticlesRepository articlesRepository;
    private final CategoriesRepository categoriesRepository;
    private final SupplierSuppliesArticleRepository supplierSuppliesArticleRepository;
    private final ArticlesAtLocationRepository articlesAtLocationRepository;

    public ArticlesServiceImpl(ArticlesRepository articlesRepository, CategoriesRepository categoriesRepository, SupplierSuppliesArticleRepository supplierSuppliesArticleRepository, ArticlesAtLocationRepository articlesAtLocationRepository) {
        this.articlesRepository = articlesRepository;
        this.categoriesRepository = categoriesRepository;
        this.supplierSuppliesArticleRepository = supplierSuppliesArticleRepository;
        this.articlesAtLocationRepository = articlesAtLocationRepository;
    }

    @Override
    @Transactional
    public Optional<Articles> create(String description, String articlename, int maxquantityperlocation) {
        if(description==null || description.isEmpty() || articlename==null || articlename.isEmpty() || maxquantityperlocation<=0)
        {
            throw new InvalidArgumentsException();
        }
        return Optional.of(articlesRepository.save(new Articles(description, articlename, maxquantityperlocation)));
    }

    @Override
    @Transactional
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
        if(articles.getCategoryList().contains(category))
        {
            return Optional.of(articles);
        }
        articles.getCategoryList().add(category);
        articlesRepository.save(articles);
        return Optional.of(articles);
    }

    @Override
    public List<Categories> findAllCategoriesByArticle(Integer articleId) {
        Articles articles=articlesRepository.findById(articleId).orElseThrow(InvalidArgumentsException::new);
        return articles.getCategoryList();
    }

    @Override
    public List<Articles> findAll() {
        return articlesRepository.findAll();
    }

    @Override
    public List<SupplierSuppliesArticleReport> findAllBySupplier(Integer supplierId) {
        return supplierSuppliesArticleRepository.findAllById_Userid(supplierId);
    }

    @Override
    public List<ArticlesAtLocationReport> findAvailabilityAtAllLocations(Integer id) {
        return articlesAtLocationRepository.findAllByArticleid(id);
    }
}
