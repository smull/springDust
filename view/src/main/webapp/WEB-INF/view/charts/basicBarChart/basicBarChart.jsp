<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<script src="/js/basicBarChart.js"></script>
<div id="tab">
  <div id="tabhead">
    <ul>
      <li><a href="/diagram/about">О диаграммах</a></li>
      <li><a href="/diagram/pieChart">Круговая диаграмма</a></li>
      <li><a href="/diagram/basicColumnChart">Столбчатая диаграмма</a></li>
        <li><a href="/diagram/basicLineChart">График</a></li>
      <li class="activetab"><a href="/diagram/basicBarChart">Столбчатая</a></li>
    </ul>
  </div>
  <div id="tabcontent">
    <h3 align="center">Столбчатая диаграмма</h3>
    <p>Данная диаграмма отображает .</p>
    <label for="from">С: </label>
    <input type="text" id="from" name="from">
    <label for="to">по:</label>
    <input type="text" id="to" name="to">
    <input id="showButtonBC" type="button" value="Показать">
    <p>Статистика по годам   <input id="statByYears" name="stat" type="radio" checked ></br>
      Статистика по месяцам <input  id="statByMonths" name="stat" type="radio"  ></br> </p>
    <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

  </div>
</div>

