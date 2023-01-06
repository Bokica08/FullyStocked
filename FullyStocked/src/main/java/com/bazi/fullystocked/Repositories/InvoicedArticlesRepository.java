package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.Articles;
import com.bazi.fullystocked.Models.InvoicedArticles;
import com.bazi.fullystocked.Models.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface InvoicedArticlesRepository extends JpaRepository<InvoicedArticles, Integer> {
    List<InvoicedArticles> findAllByArticle(Articles article);
    List<InvoicedArticles> findAllByInvoice(Invoices invoice);
    Optional<InvoicedArticles> findByInvoiceAndArticle(Invoices invoice, Articles article);
}
