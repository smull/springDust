<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<<script src="/js/basicColumnChart.js"></script>
<div id="tab">
  <div id="tabhead">
    <ul>
        <li><a href="/diagram/about">О диаграммах</a></li>
        <li><a href="/diagram/pieChart">Круговая диаграмма</a></li>
        <li class="activetab"><a href="/diagram/basicColumnChart">Столбчатая диаграмма</a></li>
        <li><a href="/diagram/basicLineChart">График</a></li>
        <li ><a href="/diagram/basicBarChart">Столбчатая</a></li>
    </ul>
  </div>
  <div id="tabcontent">
    <h3 align="center">Столбчатая диаграмма</h3>
    <p>Данная диаграмма отображает среднемесячный доход от каждого типа сделки.
      Вам необходимо лишь указать диапазон дат,
      по которым желаете получить расчетные данные.</p>
    <label for="from">С: </label>
    <input type="text" id="from" name="from">
    <label for="to">по:</label>
    <input type="text" id="to" name="to">
    <input id="showButtonBC" type="button" value="Показать">
    <p>         </p>
    <div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
  </div>
</div>

