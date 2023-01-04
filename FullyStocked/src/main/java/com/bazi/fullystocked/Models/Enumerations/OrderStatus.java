package com.bazi.fullystocked.Models.Enumerations;

public enum OrderStatus {
    CREATED("Created"),
    APPROVED("Approved"),
    CANCELED("Canceled"),
    IN_PROGRESS("In progress"),
    DELIVERED("Delivered"),
    REJECTED("Rejected"),
    PROCESSED("Processed");
    private String name;

    OrderStatus(String s) {
        this.name=s;
    }
    public String getName() {
        return this.name;
    }
    public String toString() {
        return name;
    }
}
