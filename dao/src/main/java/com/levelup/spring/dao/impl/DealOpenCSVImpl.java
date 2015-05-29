package com.levelup.spring.dao.impl;

import au.com.bytecode.opencsv.CSVReader;
import com.levelup.spring.dao.ParseCSV;
import com.levelup.stock.model.Deal;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SMULL on 5/4/2015.
 */
public class DealOpenCSVImpl implements ParseCSV {

    @Override
    public List<Deal> parse(String fileName) {
        List<Deal> deals = new ArrayList<Deal>();
        CSVReader reader;
        try {
            reader=new CSVReader(new FileReader(fileName));
            String[] firstLine=reader.readNext();
            if (firstLine != null) {
                String[] nextLine  = null;
                while ((nextLine=reader.readNext()) != null) {
                    Deal deal = new Deal();
                    deal.setType(nextLine[0]);
                    deal.setTicket(nextLine[1]);
                    deal.setSymbol(nextLine[2]);
                    deal.setLots(Double.parseDouble(nextLine[3]));
                    deal.setBuySell(nextLine[4]);
                    deal.setOpenPrice(Double.parseDouble(nextLine[5]));
                    deal.setClosePrice(Double.parseDouble(nextLine[6]));
                    deal.setOpenTime(new Date(nextLine[7]));
                    deal.setCloseTime(new Date(nextLine[8]));
                    deal.setProfit(Double.parseDouble(nextLine[9]));
                    deal.setSwap(Double.parseDouble(nextLine[10]));
                    deal.setCommission(nextLine[11]);
                    deal.setTp(Double.parseDouble(nextLine[12]));
                    deal.setSl(Double.parseDouble(nextLine[13]));
                    deal.setPips(Double.parseDouble(nextLine[14]));
                    deal.setResult(nextLine[15]);
                    deal.setTradeDuration(Double.parseDouble(nextLine[16]));
                    deal.setMagicNumber(nextLine[17]);
                    deal.setOrderComment(nextLine[18]);
                    deal.setMae(Double.parseDouble(nextLine[19]));
                    deal.setMfe(Double.parseDouble(nextLine[20]));
                    deal.setfXBlueLiveAccount(nextLine[21]);

                    deals.add(deal);

               }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deals;
    }
}
