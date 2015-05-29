package com.levelup.stock.model.dto;

import javax.persistence.Entity;
import java.io.Serializable;


public class SymbolProfit implements Serializable{

    String symbol;
    Double profitSum;

    public SymbolProfit() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getProfit() {
        return profitSum;
    }

    public void setProfit(Double profitSum) {
        this.profitSum = profitSum;
    }
}
