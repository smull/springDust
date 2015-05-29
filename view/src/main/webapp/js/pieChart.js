$(document).ready(function () {
    //Выбор даты с/по
    $(function () {
        $("#from").datepicker({
            defaultDate: "+1w",
            changeMonth: true,
            numberOfMonths: 3,
            onClose: function (selectedDate) {
                $("#to").datepicker("option", "minDate", selectedDate);
            }
        });
        $("#to").datepicker({
            defaultDate: "+1w",
            changeMonth: true,
            numberOfMonths: 3,
            onClose: function (selectedDate) {
                $("#from").datepicker("option", "maxDate", selectedDate);
            }
        });
    });
//Действие по нажатию кнопки "показать"
    $("#showButtonPCPie").click(function () {

        var dateFrom = $("#from").datepicker("getDate");
        var dateTo = $("#to").datepicker("getDate");

        if ((dateFrom === null) || (dateTo === null)) {
            alert("Не все даты указаны! Пожалуйста, проверьте еще раз.");
        } else {
            //Текстовые надписи на графике
            var titleOfChart = 'Доля каждого типа сделки от общего количества сделок';
            var nameOfPositionInChart = 'Доля сделок';

//Запрос к серверу на получение данных по клику по кнопке
            $.ajax({
                type: "POST",
                url: "/diagram/pieChart/data",
                data: {dateBegin: dateFrom.getTime(), dateEnd: dateTo.getTime()},
                dataType: "json",
                success: function (dataResponse) {
                    //Парсер полученных данных для графика
                    var name = Array();
                    var data = Array();
                    var dataArrayFinal = Array();
                    for (i = 0; i < dataResponse.length; i++) {
                        name[i] = dataResponse[i].nameOfCurrency;
                        data[i] = dataResponse[i].share;
                    }

                    for (j = 0; j < name.length; j++) {
                        var temp = new Array(name[j], data[j]);
                        dataArrayFinal[j] = temp;
                    }
                    //Вызов отрисовки графика
                    showPieChart(dataArrayFinal);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(textStatus);
                }
            });
        }
//Функция отрисовки графика по полученным данным
        function showPieChart(dataArray) {

            //Оформление графика (стиль)
// Load the fonts
            Highcharts.createElement('link', {
                href: '//fonts.googleapis.com/css?family=Signika:400,700',
                rel: 'stylesheet',
                type: 'text/css'
            }, null, document.getElementsByTagName('head')[0]);

// Add the background image to the container
            Highcharts.wrap(Highcharts.Chart.prototype, 'getContainer', function (proceed) {
                proceed.call(this);
                this.container.style.background = 'url(http://www.highcharts.com/samples/graphics/sand.png)';
            });


            Highcharts.theme = {
                colors: ["#f45b5b", "#8085e9", "#8d4654", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee",
                    "#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
                chart: {
                    backgroundColor: null,
                    style: {
                        fontFamily: "Signika, serif"
                    }
                },
                title: {
                    style: {
                        color: 'black',
                        fontSize: '16px',
                        fontWeight: 'bold'
                    }
                },
                subtitle: {
                    style: {
                        color: 'black'
                    }
                },
                tooltip: {
                    borderWidth: 0
                },
                legend: {
                    itemStyle: {
                        fontWeight: 'bold',
                        fontSize: '13px'
                    }
                },
                xAxis: {
                    labels: {
                        style: {
                            color: '#6e6e70'
                        }
                    }
                },
                yAxis: {
                    labels: {
                        style: {
                            color: '#6e6e70'
                        }
                    }
                },
                plotOptions: {
                    series: {
                        shadow: true
                    },
                    candlestick: {
                        lineColor: '#404048'
                    },
                    map: {
                        shadow: false
                    }
                },

                // Highstock specific
                navigator: {
                    xAxis: {
                        gridLineColor: '#D0D0D8'
                    }
                },
                rangeSelector: {
                    buttonTheme: {
                        fill: 'white',
                        stroke: '#C0C0C8',
                        'stroke-width': 1,
                        states: {
                            select: {
                                fill: '#D0D0D8'
                            }
                        }
                    }
                },
                scrollbar: {
                    trackBorderColor: '#C0C0C8'
                },

                // General
                background2: '#E0E0E8'

            };

// Apply the theme
            Highcharts.setOptions(Highcharts.theme);

            $(function () {

                // Radialize the colors
                Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function (color) {
                    return {
                        radialGradient: {cx: 0.5, cy: 0.3, r: 0.7},
                        stops: [
                            [0, color],
                            [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
                        ]
                    };
                });

                // Build the chart
                $('#container').highcharts({
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false
                    },
                    title: {
                        text: titleOfChart
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.2f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                format: '<b>{point.name}</b>: {point.percentage:.2f} %',
                                style: {
                                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                },
                                connectorColor: 'silver'
                            }
                        }
                    },
                    series: [{
                        type: 'pie',
                        name: nameOfPositionInChart,
                        data: dataArray
                    }]
                });
            });
        };

    });
});