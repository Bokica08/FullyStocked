package com.bazi.fullystocked.Services.Implementations;

import com.bazi.fullystocked.Models.*;
import com.bazi.fullystocked.Models.Enumerations.ArticleStatus;
import com.bazi.fullystocked.Models.Enumerations.OrderPriority;
import com.bazi.fullystocked.Models.Enumerations.OrderStatus;
import com.bazi.fullystocked.Models.Exceptions.InvalidArgumentsException;
import com.bazi.fullystocked.Repositories.*;
import com.bazi.fullystocked.Services.OrdersService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final ManagersRepository managersRepository;
    private final SuppliersRepository suppliersRepository;
    private final ArticlesRepository articlesRepository;
    private final LocationsRepository locationsRepository;
    private final OrderedArticlesRepository orderedArticlesRepository;

    public OrdersServiceImpl(OrdersRepository ordersRepository, ManagersRepository managersRepository, SuppliersRepository suppliersRepository, ArticlesRepository articlesRepository, LocationsRepository locationsRepository, OrderedArticlesRepository orderedArticlesRepository) {
        this.ordersRepository = ordersRepository;
        this.managersRepository = managersRepository;
        this.suppliersRepository = suppliersRepository;
        this.articlesRepository = articlesRepository;
        this.locationsRepository = locationsRepository;
        this.orderedArticlesRepository = orderedArticlesRepository;
    }

    @Override
    public Optional<Orders> create(OrderPriority priority, Integer managerId, Integer supplierId) {
        Managers manager=managersRepository.findById(managerId).orElseThrow(InvalidArgumentsException::new);
        Suppliers supplier=suppliersRepository.findById(supplierId).orElseThrow(InvalidArgumentsException::new);
        return Optional.of(ordersRepository.save(new Orders(priority, manager, supplier)));
    }

    @Override
    public List<Orders> findAllByManager(Integer managerId) {
        Managers manager=managersRepository.findById(managerId).orElseThrow(InvalidArgumentsException::new);
        return ordersRepository.findAllByManager(manager);
    }

    @Override
    public List<Orders> findAllByManagerAndStatus(Integer managerId, OrderStatus status) {
        Managers manager=managersRepository.findById(managerId).orElseThrow(InvalidArgumentsException::new);
        return ordersRepository.findAllByManagerAndStatus(manager, status);
    }

    @Override
    @Transactional
    public Optional<Orders> addArticleToOrder(int quantity, Integer locationId, Integer articleId, Integer orderId) {
        if(quantity<=0)
        {
            throw new InvalidArgumentsException();
        }
        Locations location=locationsRepository.findById(locationId).orElseThrow(InvalidArgumentsException::new);
        Articles article=articlesRepository.findById(articleId).orElseThrow(InvalidArgumentsException::new);
        Orders order=ordersRepository.findById(orderId).orElseThrow(InvalidArgumentsException::new);
        OrderedArticles orderedArticles=orderedArticlesRepository.save(new OrderedArticles(quantity, order, location, article));
        order.getArticlesList().add(orderedArticles);
        return Optional.of(ordersRepository.save(order));
    }

    @Override
    public Optional<Orders> addManagerRemark(Integer orderId, String remark) {
        Orders order=ordersRepository.findById(orderId).orElseThrow(InvalidArgumentsException::new);
        order.setManagerremark(remark);
        return Optional.of(ordersRepository.save(order));
    }

    @Override
    public Optional<Orders> addSupplierRemark(Integer orderId, String remark) {
        Orders order=ordersRepository.findById(orderId).orElseThrow(InvalidArgumentsException::new);
        order.setSupplierremark(remark);
        return Optional.of(ordersRepository.save(order));
    }

    @Override
    public List<Orders> findAllBySupplier(Integer supplierId) {
        Suppliers supplier=suppliersRepository.findById(supplierId).orElseThrow(InvalidArgumentsException::new);
        return ordersRepository.findAllBySupplier(supplier);
    }

    @Override
    public List<Orders> findAllBySupplierAndStatus(Integer supplierId, OrderStatus status) {
        Suppliers supplier=suppliersRepository.findById(supplierId).orElseThrow(InvalidArgumentsException::new);
        return ordersRepository.findAllBySupplierAndStatus(supplier, status);
    }

    @Override
    public Optional<Orders> findById(Integer orderId) {
        return ordersRepository.findById(orderId);
    }

    @Override
    public Optional<Orders> updateStatus(Integer orderId, OrderStatus status) {
        Orders order=ordersRepository.findById(orderId).orElseThrow(InvalidArgumentsException::new);
        if(status.equals(OrderStatus.CREATED))
        {
            throw new InvalidArgumentsException();
        }
        if(status.equals(OrderStatus.APPROVED) && !order.getStatus().equals(OrderStatus.CREATED))
        {
            throw new InvalidArgumentsException();
        }
        if(status.equals(OrderStatus.REJECTED) && !order.getStatus().equals(OrderStatus.CREATED))
        {
            throw new InvalidArgumentsException();
        }
        if(status.equals(OrderStatus.IN_PROGRESS) && !order.getStatus().equals(OrderStatus.APPROVED))
        {
            throw new InvalidArgumentsException();
        }
        if(status.equals(OrderStatus.DELIVERED))
        {
            if(!order.getStatus().equals(OrderStatus.IN_PROGRESS))
            {
                throw new InvalidArgumentsException();
            }
            order.getArticlesList().forEach(orderedArticles -> orderedArticles.setArticlestatus(ArticleStatus.DELIVERED));
        }
        if(status.equals(OrderStatus.PROCESSED))
        {
            if(!order.getStatus().equals(OrderStatus.DELIVERED))
            {
                throw new InvalidArgumentsException();
            }
            if(order.getArticlesList().stream().anyMatch(orderedArticles -> !orderedArticles.getArticlestatus().equals(ArticleStatus.PROCESSED)))
            {
                throw new InvalidArgumentsException();
            }
        }
        if(status.equals(OrderStatus.CANCELED))
        {
            if(order.getStatus().equals(OrderStatus.DELIVERED) || order.getStatus().equals(OrderStatus.PROCESSED))
            {
                throw new InvalidArgumentsException();
            }
        }
        order.setStatus(status);
        return Optional.of(ordersRepository.save(order));
    }

}
