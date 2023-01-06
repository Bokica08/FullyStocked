package com.bazi.fullystocked.Models.Exceptions;

public class ArticleNotAvailableException extends RuntimeException{
    public ArticleNotAvailableException() {
        super("The article is not available in the asked quantity");
    }
}
