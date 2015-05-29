package com.levelup.stock.view;

import com.levelup.spring.dao.DealRepository;
import com.levelup.spring.dao.impl.DealRepositoryImpl;
import com.levelup.spring.service.ChartsService;
import com.levelup.stock.model.BasicColumnChart;
import com.levelup.stock.model.PieChartTest;
import com.levelup.stock.model.User;
import com.levelup.stock.model.dto.BasicBarChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/diagram")
@SessionAttributes("user")
public class DiagramController {
    @Autowired
//    DealService dealService;
            ChartsService chartsService;
    @Autowired
    DealRepository dealRepository;

    //    О диаграммах
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String viewAboutCharts(Model model) {
        return "charts.page";
    }

    //    Круговая диаграмма и передача данных для ее отрисовки
    @RequestMapping(value = "/pieChart", method = RequestMethod.GET)
    public String viewPieChart(Model model) {
        return "pieChart.page";
    }

    @RequestMapping(value = "/pieChart/data", produces = "application/json", method = RequestMethod.POST)
    public
    @ResponseBody
    List<PieChartTest> getDataListForPieChart(@ModelAttribute("user") User user, @RequestParam("dateBegin") Long dateFrom,
                                              @RequestParam("dateEnd") Long dateTo) {
//        List<PieChartTest> dataList = new ArrayList<PieChartTest>();
//        dataList.add(new PieChartTest("McDonald's", 30.5));
//        dataList.add(new PieChartTest("King Burger", 25.5));
//        dataList.add(new PieChartTest("Fufelok", 40.5));
//        dataList.add(new PieChartTest("Others", 3.5));
//        return dealService.getAllUniqe(user.getEmail(), dateFrom, dateTo);
        return chartsService.getPieChartValidData(user.getId(), dateFrom, dateTo);
    }

    //    Столбчатая диаграмма и передача данных для ее отрисовки
    @RequestMapping(value = "/basicColumnChart", method = RequestMethod.GET)
    public String viewBasicColumn(Model model) {
        return "basicColumnChart.page";
    }

    @RequestMapping(value = "/basicColumnChart/data", produces = "application/json", method = RequestMethod.POST)
    public
    @ResponseBody
    List<BasicColumnChart> getDataListForBasicColumn(@RequestParam("dateBegin") Long dateBegin,
                                                     @RequestParam("dateEnd") Long dateEnd) {
        List<BasicColumnChart> dataList = new ArrayList<BasicColumnChart>();
        List<Double> dataForChart = new ArrayList();
        dataForChart.add(10D);
        dataForChart.add(12D);
        dataForChart.add(16D);
        dataForChart.add(18D);
        dataForChart.add(14D);
        dataForChart.add(40D);
        dataForChart.add((double) 70);
        dataForChart.add(20D);
        dataForChart.add(30D);
        dataForChart.add(90D);
        dataForChart.add(100D);
        dataForChart.add(15D);

        dataList.add(new BasicColumnChart("McDonald's", dataForChart));
        dataList.add(new BasicColumnChart("King Burger", dataForChart));
        dataList.add(new BasicColumnChart("Fufelok", dataForChart));
        dataList.add(new BasicColumnChart("Others", dataForChart));
        return dataList;
    }

    @RequestMapping(value = "/basicLineChart", method = RequestMethod.GET)
    public String viewBasicLine(Model model) {
        return "basicLineChart.page";
    }

    @RequestMapping(value = "/basicLineChart/data", produces = "application/json", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Double> getDataListForBasicLine() {

        List<Double> dataList = new ArrayList<>();
        Random r = new Random();
        double rangeMin = 400;
        double rangeMax = 1000;

        for (int i = 0; i < 100; i++) {
            double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            dataList.add(randomValue);
        }
        return dataList;
    }

    @RequestMapping(value = "/basicBarChart", method = RequestMethod.GET)
    public String viewBasicBar(Model model) {
        return "basicBarChart.page";
    }

    @RequestMapping(value = "/basicBarChart/statByMonths", produces = "application/json", method = RequestMethod.POST)
    public
    @ResponseBody
    List<BasicBarChart> getDataListForBarColumnMonth(@ModelAttribute("user") User user,
                                                     @RequestParam("dateBegin") Long dateBegin,
                                                     @RequestParam("dateEnd") Long dateEnd) {

        return dealRepository.getSumProf(user.getEmail(), dateBegin, dateEnd);
    }

    @RequestMapping(value = "/basicBarChart/statByYears", produces = "application/json", method = RequestMethod.POST)
    public
    @ResponseBody
    List<BasicBarChart> getDataListForBarColumnYears(@ModelAttribute("user") User user,
                                                     @RequestParam("dateBegin") Long dateBegin,
                                                     @RequestParam("dateEnd") Long dateEnd) {

        return dealRepository.getSumProfit(user.getEmail(), dateBegin, dateEnd);
    }
}
