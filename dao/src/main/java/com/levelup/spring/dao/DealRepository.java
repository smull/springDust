package com.levelup.spring.dao;

import com.levelup.stock.model.BasicColumnChart;
import com.levelup.stock.model.Deal;
import com.levelup.stock.model.dto.BasicBarChart;
import com.levelup.stock.model.dto.SymbolProfit;

import java.util.List;

public interface DealRepository {

    public Deal getById(Long id, Class entityClass);

    public Deal create(Deal deal);

    public Deal update(Deal deal);

    public Boolean delete(Long id, Class entityClass);

    public List<String> getAllUniqeSymbol(String userEmail);

    public List<SymbolProfit> getAllUniqe(String userEmail, Long beginTime, Long endTime);

    public List<BasicBarChart> getSumProfit(String userEmail, Long beginTime, Long endTime);

    public List<BasicBarChart> getSumProf(String userEmail, Long beginTime, Long endTime);
}
