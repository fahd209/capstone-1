package com.pluralsight.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Entry {
    LocalDate date;
    LocalTime time;
    String transActionType;
    String description;
    String vendor;
    double amount;

    public Entry(LocalDate date, LocalTime time, String transActionType, String description, String vendor, double amount)
    {
        this.date = date;
        this.time = time;
        this.transActionType = transActionType;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getTransActionType() {
        return transActionType;
    }

    public void setTransActionType(String transActionType) {
        this.transActionType = transActionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
