package com.levelup.spring.dao.impl;

import com.levelup.spring.dao.ChartsRepository;
import com.levelup.stock.model.dto.PieChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class ChartsRepositoryImpl implements ChartsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    //Рекомендуемый способ создания jdbcTemplate по документации Spring.
//    Можно не использовать если в файле конфигурации создать бин на jdbcTemplate
//    @Autowired
//    public void setDataSource(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }

    @Override
    public List<PieChartData> getDataForPieChart(Long id, Long beginTime, Long endTime) {
        String sql = "SELECT symbol, count(symbol) as quantity FROM stocks.deal where date_format(closeTime, '%Y-%m-%d') "
                + "between " + "'" + new SimpleDateFormat("yyyy-MM-dd").format(beginTime) + "'"
                + " and " + "'" + new SimpleDateFormat("yyyy-MM-dd").format(endTime) + "'"
                + " and user_id=" + id.toString()
                + " group by symbol;";
        List<PieChartData> list = jdbcTemplate.query(sql, new RowMapper<PieChartData>() {
            @Override
            public PieChartData mapRow(ResultSet resultSet, int i) throws SQLException {
                PieChartData pieChartData = new PieChartData();
                pieChartData.setSymbol(resultSet.getString(1));
                pieChartData.setQuantity(resultSet.getLong(2));
                return pieChartData;
            }
        });
        return list;
    }

    @Override
    public void getDataForBasicColumnChart(Long id, Long beginTime, Long endTime) {

    }
}
