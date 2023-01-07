package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.SqlViews.OrdersReport;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface OrdersReportRepository extends JpaRepository<OrdersReport, Integer> {
    List<OrdersReport> findAllBySupplieruserid(Integer id);
    List<OrdersReport> findAllByManageruserid(Integer id);
}
