package com.bazi.fullystocked.Services.Implementations;

import com.bazi.fullystocked.Models.Enumerations.ArticleStatus;
import com.bazi.fullystocked.Models.Enumerations.OrderStatus;
import com.bazi.fullystocked.Models.Exceptions.InvalidArgumentsException;
import com.bazi.fullystocked.Models.OrderedArticles;
import com.bazi.fullystocked.Models.SqlViews.ArticlesReport;
import com.bazi.fullystocked.Models.SqlViews.SupplierSuppliesArticleReport;
import com.bazi.fullystocked.Models.StoredArticles;
import com.bazi.fullystocked.Repositories.ArticlesReportRepository;
import com.bazi.fullystocked.Repositories.OrderedArticlesRepository;
import com.bazi.fullystocked.Repositories.StoredArticlesRepository;
import com.bazi.fullystocked.Repositories.SupplierSuppliesArticleRepository;
import com.bazi.fullystocked.Services.OrdersService;
import com.bazi.fullystocked.Services.StoredArticlesService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StoredArticlesServiceImpl implements StoredArticlesService {
    private final SupplierSuppliesArticleRepository supplierSuppliesArticleRepository;
    private final OrderedArticlesRepository orderedArticlesRepository;
    private final StoredArticlesRepository storedArticlesRepository;
    private final OrdersService ordersService;
    private final ArticlesReportRepository articlesReportRepository;

    public StoredArticlesServiceImpl(SupplierSuppliesArticleRepository supplierSuppliesArticleRepository, OrderedArticlesRepository orderedArticlesRepository, StoredArticlesRepository storedArticlesRepository, OrdersService ordersService, ArticlesReportRepository articlesReportRepository) {
        this.supplierSuppliesArticleRepository = supplierSuppliesArticleRepository;
        this.orderedArticlesRepository = orderedArticlesRepository;
        this.storedArticlesRepository = storedArticlesRepository;
        this.ordersService = ordersService;
        this.articlesReportRepository = articlesReportRepository;
    }

    @Override
    public List<SupplierSuppliesArticleReport> findAllBySupplier(Integer supplierId) {
        return supplierSuppliesArticleRepository.findAllById_Userid(supplierId);
    }

    @Override
    @Transactional
    public Optional<StoredArticles> updateFromOrder(Integer oarticleid) {
        OrderedArticles orderedArticle=orderedArticlesRepository.findById(oarticleid).orElseThrow(InvalidArgumentsException::new);
        if(!orderedArticle.getArticlestatus().equals(ArticleStatus.DELIVERED))
        {
            throw new InvalidArgumentsException();
        }
        StoredArticles storedArticle=storedArticlesRepository.findByArticleAndLocations(orderedArticle.getArticle(), orderedArticle.getLocation()).orElseThrow(InvalidArgumentsException::new);
        storedArticle.setQuantity(storedArticle.getQuantity()+ orderedArticle.getQuantity());
        orderedArticle.setArticlestatus(ArticleStatus.PROCESSED);
        ordersService.updateStatus(orderedArticle.getOrder().getOrderid(), OrderStatus.PROCESSED);
        storedArticlesRepository.save(storedArticle);
        orderedArticlesRepository.save(orderedArticle);
        return Optional.of(storedArticle);
    }

    @Override
    public List<ArticlesReport> findByLocation(Integer locationId) {
        return articlesReportRepository.findAllByLocationid(locationId);
    }
}
