package com.levelup.stock.model;

import java.util.ArrayList;
import java.util.List;

public class BasicColumnChart {
    private String name;
    private List<Double> data;

    public BasicColumnChart() {
    }

    public BasicColumnChart(String name, List<Double> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setNameOfCurrency(String name) {
        this.name = name;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }
}
