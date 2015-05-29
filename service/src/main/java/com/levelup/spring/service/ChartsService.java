package com.levelup.spring.service;

import com.levelup.stock.model.PieChartTest;

import java.util.List;

public interface ChartsService {
    public List<PieChartTest> getPieChartValidData(Long id, Long beginTime, Long endTime);
}
