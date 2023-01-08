package com.bazi.fullystocked.Models.Enumerations;

public enum OrderPriority {
    LOW("Low"), MEDIUM("Medium"), HIGH("High");
    private String name;

    OrderPriority(String s) {
        this.name=s;
    }
    public String getName() {
        return this.name;
    }
    public String toString() {
        return name;
    }
}
