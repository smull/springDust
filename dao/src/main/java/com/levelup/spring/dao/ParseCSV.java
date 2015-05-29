package com.levelup.spring.dao;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.levelup.stock.model.Deal;

import java.util.List;

/**
 * Created by SMULL on 5/4/2015.
 */
public interface ParseCSV {
    List<Deal> parse(String fileName);
}
