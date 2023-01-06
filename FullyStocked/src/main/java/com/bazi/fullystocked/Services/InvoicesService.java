package com.bazi.fullystocked.Services;

import com.bazi.fullystocked.Models.Invoices;

import java.util.*;

public interface InvoicesService {
    Optional<Invoices> create(String customername, String customerphone, String street, int streetnumber, String city, Integer workerId);
    Optional<Invoices> create(Integer workerId);
    Optional<Invoices> addArticleToInvoice(Integer invoiceId, Integer articleId, int price, int quantity);
    Optional<Invoices> findById(Integer id);
}
