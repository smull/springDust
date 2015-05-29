package com.levelup.spring.service.impl;

import com.levelup.spring.dao.ChartsRepository;
import com.levelup.spring.service.ChartsService;
import com.levelup.stock.model.PieChartTest;
import com.levelup.stock.model.dto.PieChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("chartsService")
public class ChartsServiceImpl implements ChartsService{

    @Autowired
    ChartsRepository chartsRepository;

    public List<PieChartTest> getPieChartValidData(Long id, Long beginTime, Long endTime) {
        List<PieChartData> list = chartsRepository.getDataForPieChart(id, beginTime, endTime);
        List<PieChartTest> pieChartTestList = new ArrayList<PieChartTest>();
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).getQuantity();
        }
        for (int i = 0; i < list.size(); i++) {
            pieChartTestList.add(new PieChartTest());
            pieChartTestList.get(i).setNameOfCurrency(list.get(i).getSymbol());
            pieChartTestList.get(i).setShare(((double) Math.round((list.get(i).getQuantity().doubleValue() / sum * 10000))) / 100);
        }
        return pieChartTestList;
    }

}
