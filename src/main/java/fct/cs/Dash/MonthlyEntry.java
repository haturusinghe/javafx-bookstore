package fct.cs.Dash;

import java.sql.Date;

public class MonthlyEntry {
    private long orderYear;
    private String orderMonth;
    private int salesPerMonth;
    private int quantityPerMonth;
    private int discountPerMonth;
    private int profitPerMonth;
    private int ordersPerMonth;

    public MonthlyEntry(long orderYear, String orderMonth, int salesPerMonth, int quantityPerMonth, int discountPerMonth, int ordersPerMonth) {
        this.orderYear = orderYear;
        this.orderMonth = orderMonth;
        this.salesPerMonth = salesPerMonth;
        this.quantityPerMonth = quantityPerMonth;
        this.discountPerMonth = discountPerMonth;
        this.ordersPerMonth = ordersPerMonth;
        this.profitPerMonth = salesPerMonth - discountPerMonth;
    }

    public int getProfitPerMonth() {
        return profitPerMonth;
    }

    public void setProfitPerMonth(int profitPerMonth) {
        this.profitPerMonth = profitPerMonth;
    }

    public long getOrderYear() {
        return orderYear;
    }

    public void setOrderYear(long orderYear) {
        this.orderYear = orderYear;
    }

    public String getOrderMonth() {
        return orderMonth;
    }

    public void setOrderMonth(String orderMonth) {
        this.orderMonth = orderMonth;
    }

    public int getSalesPerMonth() {
        return salesPerMonth;
    }

    public void setSalesPerMonth(int salesPerMonth) {
        this.salesPerMonth = salesPerMonth;
    }

    public int getQuantityPerMonth() {
        return quantityPerMonth;
    }

    public void setQuantityPerMonth(int quantityPerMonth) {
        this.quantityPerMonth = quantityPerMonth;
    }

    public int getDiscountPerMonth() {
        return discountPerMonth;
    }

    public void setDiscountPerMonth(int discountPerMonth) {
        this.discountPerMonth = discountPerMonth;
    }

    public int getOrdersPerMonth() {
        return ordersPerMonth;
    }

    public void setOrdersPerMonth(int ordersPerMonth) {
        this.ordersPerMonth = ordersPerMonth;
    }
}
