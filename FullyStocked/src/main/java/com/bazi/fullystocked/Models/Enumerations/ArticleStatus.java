package com.bazi.fullystocked.Models.Enumerations;

public enum ArticleStatus {
    ORDERED("Ordered"), DELIVERED("Delivered"), PROCESSED("Processed");
    private String name;

    ArticleStatus(String s) {
        this.name=s;
    }
    public String getName() {
        return this.name;
    }
    public String toString() {
        return name;
    }
}
