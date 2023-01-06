package com.bazi.fullystocked.Models.Exceptions;

public class ArticleMaxQuantityException extends RuntimeException{
    public ArticleMaxQuantityException() {
        super("The order exceeds the maximum allowed quantity of the article");
    }
}
