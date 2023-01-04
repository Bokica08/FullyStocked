package com.bazi.fullystocked.Services.Implementations;

import com.bazi.fullystocked.Models.Articles;
import com.bazi.fullystocked.Models.Exceptions.InvalidArgumentsException;
import com.bazi.fullystocked.Models.InvoicedArticles;
import com.bazi.fullystocked.Models.Invoices;
import com.bazi.fullystocked.Models.Workers;
import com.bazi.fullystocked.Repositories.ArticlesRepository;
import com.bazi.fullystocked.Repositories.InvoicedArticlesRepository;
import com.bazi.fullystocked.Repositories.InvoicesRepository;
import com.bazi.fullystocked.Repositories.WorkersRepository;
import com.bazi.fullystocked.Services.InvoicesService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class InvoicesServiceImpl implements InvoicesService {
    private final InvoicedArticlesRepository invoicedArticlesRepository;
    private final InvoicesRepository invoicesRepository;
    private final WorkersRepository workersRepository;
    private final ArticlesRepository articlesRepository;

    public InvoicesServiceImpl(InvoicedArticlesRepository invoicedArticlesRepository, InvoicesRepository invoicesRepository, WorkersRepository workersRepository, ArticlesRepository articlesRepository) {
        this.invoicedArticlesRepository = invoicedArticlesRepository;
        this.invoicesRepository = invoicesRepository;
        this.workersRepository = workersRepository;
        this.articlesRepository = articlesRepository;
    }

    @Override
    public Optional<Invoices> create(String customername, String customerphone, String street, int streetnumber, String city, Integer workerId) {
        Workers worker = workersRepository.findById(workerId).orElseThrow(InvalidArgumentsException::new);
        return Optional.of(invoicesRepository.save(new Invoices(customername, customerphone, street, streetnumber, city, worker)));
    }

    @Override
    public Optional<Invoices> create(Integer workerId) {
        Workers worker = workersRepository.findById(workerId).orElseThrow(InvalidArgumentsException::new);
        return Optional.of(invoicesRepository.save(new Invoices(worker)));
    }

    @Override
    @Transactional
    public Optional<Invoices> addArticleToInvoice(Integer invoiceId, Integer articleId, int price, int quantity) {
        Invoices invoice = invoicesRepository.findById(invoiceId).orElseThrow(InvalidArgumentsException::new);
        Articles article = articlesRepository.findById(articleId).orElseThrow(InvalidArgumentsException::new);
        if(price<=0 || quantity<=0)
        {
            throw new InvalidArgumentsException();
        }
        InvoicedArticles invoicedArticles=new InvoicedArticles(price, quantity, invoice, article);
        invoicedArticlesRepository.save(invoicedArticles);
        invoice.getArticlesList().add(invoicedArticles);
        return Optional.of(invoicesRepository.save(invoice));
    }
}
