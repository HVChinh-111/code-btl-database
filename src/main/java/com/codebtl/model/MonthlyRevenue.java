package com.codebtl.model;

import java.math.BigDecimal;

public class MonthlyRevenue {
    private int month;
    private int year;
    private BigDecimal totalRevenue;

    public MonthlyRevenue() {
    }

    public MonthlyRevenue(int month, int year, BigDecimal totalRevenue) {
        this.month = month;
        this.year = year;
        this.totalRevenue = totalRevenue;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlyRevenue that = (MonthlyRevenue) o;
        return month == that.month && year == that.year;
    }

    @Override
    public int hashCode() {
        return 31 * year + month;
    }
}
