package com.bazi.fullystocked.Models.Exceptions;

public class ArticleAlreadyInInvoiceException extends RuntimeException{
    public ArticleAlreadyInInvoiceException() {
        super("Article is already added to the invoice");
    }
}
