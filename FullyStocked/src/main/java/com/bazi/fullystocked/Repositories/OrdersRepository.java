package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.Enumerations.OrderPriority;
import com.bazi.fullystocked.Models.Enumerations.OrderStatus;
import com.bazi.fullystocked.Models.Managers;
import com.bazi.fullystocked.Models.OrderedArticles;
import com.bazi.fullystocked.Models.Orders;
import com.bazi.fullystocked.Models.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findAllByManager(Managers manager);
    List<Orders> findAllByManagerAndStatus(Managers manager, OrderStatus status);
    List<Orders> findAllByManagerAndPriority(Managers manager, OrderPriority priority);
    List<Orders> findAllBySupplierAndStatus(Suppliers supplier, OrderStatus status);
    List<Orders> findAllBySupplierAndPriority(Suppliers supplier, OrderPriority priority);
    List<Orders> findAllBySupplier(Suppliers supplier);
    List<Orders> findAllByPriority(OrderPriority priority);
    List<Orders> findAllByStatus(OrderStatus status);
    List<Orders> findAllByDateapprovedBetween(LocalDateTime from, LocalDateTime to);
    List<Orders> findAllByDatecreatedBetween(LocalDateTime from, LocalDateTime to);
    List<Orders> findAllByDateapprovedBetweenAndPriority(LocalDateTime from, LocalDateTime to, OrderPriority priority);
    List<Orders> findAllByDatecreatedBetweenAndPriority(LocalDateTime from, LocalDateTime to, OrderPriority priority);
    List<Orders> findAllByDateapprovedBetweenAndSupplier(LocalDateTime from, LocalDateTime to, Suppliers supplier);
    List<Orders> findAllByDatecreatedBetweenAndSupplier(LocalDateTime from, LocalDateTime to, Suppliers supplier);
    List<Orders> findAllByDateapprovedBetweenAndManager(LocalDateTime from, LocalDateTime to, Managers manager);
    List<Orders> findAllByDatecreatedBetweenAndManager(LocalDateTime from, LocalDateTime to, Managers manager);
    List<Orders> findAllByArticlesListContaining(OrderedArticles article);
}
