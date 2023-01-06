package com.bazi.fullystocked.Models.Exceptions;

public class ArticleAlreadyInOrderException extends RuntimeException{
    public ArticleAlreadyInOrderException() {
        super("Article is already added to the order");
    }
}
