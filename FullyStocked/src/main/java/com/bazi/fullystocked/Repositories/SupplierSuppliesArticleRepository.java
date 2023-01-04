package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.SqlViews.SupplierSuppliesArticleReport;
import com.bazi.fullystocked.Models.SqlViews.SupplierSuppliesArticleReportId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface SupplierSuppliesArticleRepository extends JpaRepository<SupplierSuppliesArticleReport, SupplierSuppliesArticleReportId> {
    List<SupplierSuppliesArticleReport> findAllById_Articleid(Integer id);
    List<SupplierSuppliesArticleReport> findAllById_Userid(Integer id);
}
