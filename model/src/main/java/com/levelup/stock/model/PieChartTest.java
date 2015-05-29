package com.levelup.stock.model;

public class PieChartTest {
    private String nameOfCurrency;
    private Double share;

    public PieChartTest() {
    }

    public PieChartTest(String nameOfCurrency, Double share) {
        this.nameOfCurrency = nameOfCurrency;
        this.share = share;

    }

    public String getNameOfCurrency() {
        return nameOfCurrency;
    }

    public void setNameOfCurrency(String nameOfCurrency) {
        this.nameOfCurrency = nameOfCurrency;
    }

    public Double getShare() {
        return share;
    }

    public void setShare(Double share) {
        this.share = share;
    }
}
