package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.SqlViews.InvoicedArticlesReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface InvoicedArticlesReportRepository extends JpaRepository<InvoicedArticlesReport, Integer> {
    List<InvoicedArticlesReport> findAllByInvoiceid(Integer invoiceid);
}
