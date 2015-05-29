<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><tiles:getAsString name="main.title"></tiles:getAsString></title>
    <link href="/css/mainPage/style.css" rel="stylesheet" type="text/css"/>
    <link href="/css/mainPage/button.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="/js/jquery-ui-1.11.2/jquery-ui.css"/>
    <script src="/js/jquery-1.11.2.js"></script>
    <script src="/js/jquery-ui-1.11.2/jquery-ui.js"></script>
    <script src="/js/dust-full.js"></script>
    <script src="/js/highCharts/highcharts.js"></script>
    <script src="/js/highCharts/modules/exporting.js"></script>
    <script src="/js/pieChart.js"></script>


    <script type="application/javascript" src="/js/basicLineChart.js"></script>

</head>

<body>
<div id="wrap">
    <div id="header">
        <tiles:insertAttribute name="main.header"></tiles:insertAttribute>
    </div>
    <div id="content">
        <div id="sidebar">
            <tiles:insertAttribute name="main.menu"></tiles:insertAttribute>
        </div>
        <div id="left">
            <tiles:insertAttribute name="main.content"></tiles:insertAttribute>
        </div>
        <div class="clear"></div>
        <div id="footer">
            <tiles:insertAttribute name="main.footer"></tiles:insertAttribute>
        </div>
    </div>
</div>
</body>
</html>
