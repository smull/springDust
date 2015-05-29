package com.levelup.stock.model.dto;


public class PieChartData {
    private String symbol;
    private  Long quantity;

    public PieChartData() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
