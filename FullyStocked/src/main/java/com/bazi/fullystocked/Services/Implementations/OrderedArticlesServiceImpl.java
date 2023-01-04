package com.bazi.fullystocked.Services.Implementations;

import com.bazi.fullystocked.Models.Enumerations.ArticleStatus;
import com.bazi.fullystocked.Models.Exceptions.InvalidArgumentsException;
import com.bazi.fullystocked.Models.OrderedArticles;
import com.bazi.fullystocked.Models.SqlViews.OrderedArticlesReport;
import com.bazi.fullystocked.Repositories.OrderedArticlesReportRepository;
import com.bazi.fullystocked.Repositories.OrderedArticlesRepository;
import com.bazi.fullystocked.Services.OrderedArticlesService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderedArticlesServiceImpl implements OrderedArticlesService {
    private final OrderedArticlesReportRepository orderedArticlesReportRepository;
    private final OrderedArticlesRepository orderedArticlesRepository;

    public OrderedArticlesServiceImpl(OrderedArticlesReportRepository orderedArticlesReportRepository, OrderedArticlesRepository orderedArticlesRepository) {
        this.orderedArticlesReportRepository = orderedArticlesReportRepository;
        this.orderedArticlesRepository = orderedArticlesRepository;
    }

    @Override
    public List<OrderedArticlesReport> findAllByOrder(Integer orderId) {
        return orderedArticlesReportRepository.findAllByOrderid(orderId);
    }

    @Override
    @Transactional
    public Optional<OrderedArticles> update(Integer oarticleid, int price, int quantity) {
        OrderedArticles orderedArticles=orderedArticlesRepository.findById(oarticleid).orElseThrow(InvalidArgumentsException::new);
        if(price<=0 || quantity<=0)
        {
            throw new InvalidArgumentsException();
        }
        orderedArticles.setPrice(price);
        orderedArticles.setQuantity(quantity);
        return Optional.of(orderedArticlesRepository.save(orderedArticles));
    }

    @Override
    public List<OrderedArticlesReport> findByStatusAtLocation(ArticleStatus status, Integer locationid) {
        return orderedArticlesReportRepository.findAllByArticlestatusAndLocationid(status, locationid);
    }

    @Override
    public Optional<OrderedArticlesReport> findById(Integer oarticleid) {
        return orderedArticlesReportRepository.findById(oarticleid);
    }
}
