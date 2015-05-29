$(document).ready(function () {

    $("#showButtonBCLine").click(function () {
        $.ajax({
            type: "POST",
            url: "/diagram/basicLineChart/data",
            //data: {dateBegin: dateFrom.getTime(), dateEnd: dateTo.getTime()},
            dataType: "json",
            success: function (dataResponse) {
                //Вызов отрисовки графика
                showBasicLineChart(dataResponse);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(textStatus);
            }
        });
    });


    function showBasicLineChart(dataResponse) {

        $(function () {
            $('#container').highcharts({
                chart: {
                    zoomType: 'x'
                },
                title: {
                    text: '2007-2009'
                },
                subtitle: {
                    text: document.ontouchstart === undefined ?
                        'Click and drag in the plot area to zoom in' :
                        'Pinch the chart to zoom in'
                },
                xAxis: {
                    type: 'datetime',
                    minRange: 14 * 24 * 3600000 // fourteen days
                },
                yAxis: {
                    title: {
                        text: 'Exchange rate'
                    }
                },
                legend: {
                    enabled: false
                },
                plotOptions: {
                    area: {
                        fillColor: {
                            linearGradient: {x1: 0, y1: 0, x2: 0, y2: 1},
                            stops: [
                                [0, Highcharts.getOptions().colors[0]],
                                [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                            ]
                        },
                        marker: {
                            radius: 2
                        },
                        lineWidth: 1,
                        states: {
                            hover: {
                                lineWidth: 1
                            }
                        },
                        threshold: null
                    }
                },

                series: [{
                    type: 'area',
                    name: 'USD to EUR',
                    pointInterval: 24 * 3600 * 1000,
                    pointStart: Date.UTC(2006, 0, 1),
                    data: dataResponse
                }]
            });
        });
    }
});


