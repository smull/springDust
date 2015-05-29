package com.levelup.spring.dao.impl;

import com.levelup.spring.dao.AbstractRepository;
import com.levelup.spring.dao.DealRepository;
import com.levelup.spring.dao.UserRepository;
import com.levelup.stock.model.dto.BasicBarChart;
import com.levelup.stock.model.Deal;
import com.levelup.stock.model.dto.SymbolProfit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

@Repository("dealRepository")
@Transactional
public class DealRepositoryImpl extends AbstractRepository<Deal> implements DealRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    UserRepository userRepository;

    public DealRepositoryImpl() {
    }

    @Override
    public List<String> getAllUniqeSymbol(String userEmail) {
        Long userId = userRepository.getUserByEmail(userEmail).get(0).getId();
        Query query = entityManager.createQuery("SELECT DISTINCT d.symbol from Deal d where d.userId=:userID");
        query.setParameter("userID", userId);
        List<String> uniqeSymbols = query.getResultList();
        return uniqeSymbols;
    }

    @Override
    public List<SymbolProfit> getAllUniqe(String userEmail, Long beginTime, Long endTime) {

        java.util.Date beginDate = new Date(beginTime);
        java.util.Date endDate = new Date(endTime);
        Object beginD = new java.sql.Timestamp(beginDate.getTime());
        Object endD = new java.sql.Timestamp(endDate.getTime());
        try {
            Long userId = userRepository.getUserByEmail(userEmail).get(0).getId();
//        String queryStr="select d.symbol, sum(d.profit) as profitSum from Deal d group by d.symbol";
            //      TypedQuery<SymbolProfit> query = entityManager.createQuery("select d.symbol, sum(d.profit) as profitSum from Deal d where d.closeTime>:param and d.userId=:userID group by d.symbol", SymbolProfit.class);
            Query query = entityManager.createQuery("select d.symbol, sum(d.profit) as profitSum from Deal d where d.closeTime Between:beginD and :endD and d.userId=:userID group by d.symbol");
            query.setParameter("userID", userId);
            query.setParameter("beginD", beginD);
            query.setParameter("endD", endD);
//        List<SymbolProfit> queryResultList = query.getResultList();
            List<Object[]> queryResultList = query.getResultList();

            List<SymbolProfit> listSymbolProfits = new ArrayList<>();

            for (Object[] result : queryResultList) {
                SymbolProfit symbolProfit = new SymbolProfit();
                symbolProfit.setSymbol(result[0].toString());
                symbolProfit.setProfit(Double.parseDouble(result[1].toString()));
                listSymbolProfits.add(symbolProfit);
            }
            return listSymbolProfits;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<BasicBarChart> getSumProfit(String userEmail, Long beginTime, Long endTime) {
        try {
        Object beginD = new java.sql.Timestamp(beginTime);
        Object endD = new java.sql.Timestamp(endTime);

            Long userId = userRepository.getUserByEmail(userEmail).get(0).getId();
            Query query = entityManager.createQuery("select d.symbol, round(sum(d.profit),2) as profitSum, Year(d.closeTime)  from Deal d where d.closeTime Between :beginD and :endD and d.userId=:userID group by date_format(d.closeTime, '%y'), d.symbol");

            query.setParameter("userID", userId);
            query.setParameter("beginD", beginD);
            query.setParameter("endD", endD);

            List<Object[]> queryResultList = query.getResultList();
            List<BasicBarChart> listBasicBarChart = new ArrayList<>();
            Set<String> years = new TreeSet<>();
            boolean flag = false;

            for (int i = 0; i < queryResultList.size(); i++) {
                ArrayList<Double> listDoubleData = new ArrayList<>();
                Object[] temp = queryResultList.get(i);

                for (int j = 0; j < listBasicBarChart.size(); j++) {
                    if (temp[0].equals(listBasicBarChart.get(j).getName())) {
                        listDoubleData.addAll(listBasicBarChart.get(j).getData());
                        listDoubleData.add(Double.parseDouble(temp[1].toString()));
                        listBasicBarChart.get(j).setData(listDoubleData);
                        years.add(temp[2].toString());
                        listBasicBarChart.get(j).setYear(years);
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    continue;
                } else {
                    listDoubleData = new ArrayList<>();
                    BasicBarChart basicBarChart = new BasicBarChart();
                    basicBarChart.setName(temp[0].toString());
                    listDoubleData.add(Double.parseDouble(temp[1].toString()));
                    basicBarChart.setData(listDoubleData);
                    years.add(temp[2].toString());
                    basicBarChart.setYear(years);
                    listBasicBarChart.add(basicBarChart);
                }
            }
            return listBasicBarChart;

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

@Override
    public List<BasicBarChart> getSumProf(String userEmail, Long beginTime, Long endTime) {
        try {
            Object beginD = new java.sql.Timestamp(beginTime);
            Object endD = new java.sql.Timestamp(endTime);

            Long userId = userRepository.getUserByEmail(userEmail).get(0).getId();
            Query query = entityManager.createQuery("select d.symbol, round(sum(d.profit),2) as profitSum, date_format(d.closeTime, '20%y/%m') as da from Deal d where d.closeTime Between :beginD and :endD and d.userId=:userID group by date_format(d.closeTime, '%m'), d.symbol Order by date_format(closeTime, '%y %m') asc");

            query.setParameter("userID", userId);
            query.setParameter("beginD", beginD);
            query.setParameter("endD", endD);

            List<Object[]> queryResultList = query.getResultList();
            List<BasicBarChart> listBasicBarChart = new ArrayList<>();
            Set<String> years = new TreeSet<>();
            boolean flag = false;

            for (int i = 0; i < queryResultList.size(); i++) {
                ArrayList<Double> listDoubleData = new ArrayList<>();
                Object[] temp = queryResultList.get(i);

                for (int j = 0; j < listBasicBarChart.size(); j++) {
                    if (temp[0].equals(listBasicBarChart.get(j).getName())) {
                        listDoubleData.addAll(listBasicBarChart.get(j).getData());
                        listDoubleData.add(Double.parseDouble(temp[1].toString()));
                        listBasicBarChart.get(j).setData(listDoubleData);
                        years.add(temp[2].toString());
                        listBasicBarChart.get(j).setYear(years);
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    continue;
                } else {
                    listDoubleData = new ArrayList<>();
                    BasicBarChart basicBarChart = new BasicBarChart();
                    basicBarChart.setName(temp[0].toString());
                    listDoubleData.add(Double.parseDouble(temp[1].toString()));
                    basicBarChart.setData(listDoubleData);
                    years.add(temp[2].toString());
                    basicBarChart.setYear(years);
                    listBasicBarChart.add(basicBarChart);
                }
            }
            return listBasicBarChart;

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}