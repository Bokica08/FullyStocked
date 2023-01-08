package com.bazi.fullystocked.Models.Exceptions;

public class ArticleAlreadyInQuestionException extends RuntimeException{
    public ArticleAlreadyInQuestionException() {
        super("Article is already added to the question");
    }
}
