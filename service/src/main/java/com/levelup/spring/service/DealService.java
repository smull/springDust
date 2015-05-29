package com.levelup.spring.service;

import com.levelup.stock.model.Deal;
import com.levelup.stock.model.PieChartTest;
import com.levelup.stock.model.dto.SymbolProfit;

import java.util.List;

public interface DealService {

    public Deal getById(Long id);

    public Deal create(Deal deal);

    public Deal update(Deal deal);

    public Boolean delete(Long id);

    public List<String> getAllUniqeSymbol(String userEmail);

    public List<PieChartTest> getAllUniqe(String userEmail, Long beginTime, Long endTime);
}
