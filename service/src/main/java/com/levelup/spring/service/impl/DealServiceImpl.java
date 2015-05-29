package com.levelup.spring.service.impl;


import com.levelup.spring.dao.ChartsRepository;
import com.levelup.spring.dao.DealRepository;
import com.levelup.spring.service.DealService;
import com.levelup.stock.model.Deal;
import com.levelup.stock.model.PieChartTest;
import com.levelup.stock.model.dto.SymbolProfit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("dealService")
public class DealServiceImpl implements DealService {

    @Autowired
    DealRepository dealRepository;
    ChartsRepository chartsRepository;

    public DealServiceImpl() {
    }

    @Override
    public Deal getById(Long id) {
        return dealRepository.getById(id, Deal.class);
    }

    @Override
    public Deal create(Deal deal) {
        return dealRepository.create(deal);
    }

    @Override
    public Deal update(Deal deal) {
        return dealRepository.update(deal);
    }

    @Override
    public Boolean delete(Long id) {
        return dealRepository.delete(id, Deal.class);
    }

    @Override
    public List<String> getAllUniqeSymbol(String userEmail) {
        return dealRepository.getAllUniqeSymbol(userEmail);
    }

    @Override
    public List<PieChartTest> getAllUniqe(String userEmail, Long beginTime, Long endTime) {
        List<SymbolProfit> list = (ArrayList) dealRepository.getAllUniqe(userEmail, beginTime, endTime);
        List<PieChartTest> pieChartTestList = new ArrayList<PieChartTest>();
        int count = 0;
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProfit() < 0) {

                sum += list.get(i).getProfit();
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProfit() < 0) {
                pieChartTestList.add(new PieChartTest());
                pieChartTestList.get(count).setNameOfCurrency(list.get(i).getSymbol());
                pieChartTestList.get(count).setShare(((double) Math.round((list.get(i).getProfit() / sum * 10000))) / 100);
                count++;
            }
        }
        return pieChartTestList;

    }
}
