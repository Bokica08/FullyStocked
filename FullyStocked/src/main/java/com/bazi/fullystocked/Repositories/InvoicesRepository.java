package com.bazi.fullystocked.Repositories;

import com.bazi.fullystocked.Models.InvoicedArticles;
import com.bazi.fullystocked.Models.Invoices;
import com.bazi.fullystocked.Models.Locations;
import com.bazi.fullystocked.Models.Workers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoices, Integer> {
    List<Invoices> findAllByWorker(Workers worker);
    List<Invoices> findAllByCustomernameContainingIgnoreCase(String name);
    List<Invoices> findAllByDatecreateBetween(LocalDateTime from, LocalDateTime to);
    List<Invoices> findAllByDatecreateBetweenAndWorker(LocalDateTime from, LocalDateTime to, Workers worker);
    List<Invoices> findAllByWorker_Location(Locations location);
    List<Invoices> findAllByWorker_LocationAndDatecreateBetween(Locations location, LocalDateTime from, LocalDateTime to);
    List<Invoices> findAllByArticlesListContaining(InvoicedArticles article);
}
