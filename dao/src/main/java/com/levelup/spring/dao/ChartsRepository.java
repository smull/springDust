package com.levelup.spring.dao;

import com.levelup.stock.model.dto.PieChartData;

import java.util.List;

/**
 * Created by Eugene on 17.05.2015.
 */
public interface ChartsRepository {
    public List<PieChartData> getDataForPieChart (Long id, Long beginTime, Long endTime);
    public void getDataForBasicColumnChart (Long id, Long beginTime, Long endTime);
}
