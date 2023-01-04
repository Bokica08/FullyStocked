package com.bazi.fullystocked.Services;

import com.bazi.fullystocked.Models.Enumerations.OrderPriority;
import com.bazi.fullystocked.Models.Enumerations.OrderStatus;
import com.bazi.fullystocked.Models.Orders;
import java.util.*;

public interface OrdersService {
    Optional<Orders> create(OrderPriority priority, Integer managerId, Integer supplierId);
    List<Orders> findAllByManager(Integer managerId);
    List<Orders> findAllByManagerAndStatus(Integer managerId, OrderStatus status);
    Optional<Orders> addArticleToOrder(int quantity, Integer locationId, Integer articleId, Integer orderId);
    Optional<Orders> addManagerRemark(Integer orderId, String remark);
    Optional<Orders> addSupplierRemark(Integer orderId, String remark);
    List<Orders> findAllBySupplier(Integer supplierId);
    List<Orders> findAllBySupplierAndStatus(Integer supplierId, OrderStatus status);
    Optional<Orders> findById(Integer orderId);
    Optional<Orders> updateStatus(Integer orderId, OrderStatus status);

}
