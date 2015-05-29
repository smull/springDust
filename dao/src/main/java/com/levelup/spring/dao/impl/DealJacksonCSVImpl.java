package com.levelup.spring.dao.impl;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.levelup.spring.dao.ParseCSV;
import com.levelup.stock.model.Deal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by SMULL on 5/4/2015.
 */

//JACKSON не получается указать конкретный тип данных
//CsvSchema.ColumnType.NUMBER
public class DealJacksonCSVImpl implements ParseCSV {


    @Override
    public List<Deal> parse(String fileName) {
        File file = new File(fileName);
        List<Deal> deals = new ArrayList<Deal>();
        CsvMapper mapper = new CsvMapper();
        mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
        //CsvSchema schema = mapper.schemaFor(Deal.class);
        CsvSchema schema = CsvSchema.builder()
                .addColumn("type")
                .addColumn("ticket")
                .addColumn("symbol")
                .addColumn("lots",CsvSchema.ColumnType.NUMBER)
                .addColumn("buySell")
                .addColumn("openPrice")
                .addColumn("closePrice")
                .addColumn("openTime")
                .addColumn("closeTime")
                .addColumn("profit")
                .addColumn("swap")
                .addColumn("commission")
                .addColumn("tp")
                .addColumn("sl")
                .addColumn("pips")
                .addColumn("result")
                .addColumn("tradeDuration")
                .addColumn("magicNumber")
                .addColumn("orderComment")
                .addColumn("mae")
                .addColumn("mfe")
                .addColumn("fXBlueLiveAccount")
                .build();

        MappingIterator<Map.Entry> it = null;
        try {
            it = mapper
                    .reader(Deal.class)
                    .with(schema)
                    .readValues(file);

            it.nextValue();
            it.nextValue();

            while (it.hasNextValue()) {
                Deal deal = (Deal) it.nextValue();
                System.out.println(deal.getSymbol());
                //deals.add(deal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deals;
    }
}
