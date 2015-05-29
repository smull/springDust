<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebartop">

</div>
<div id="sidebarcontent">
  <h2>Меню пользователя</h2>
  <ul>
    <li>
      <a href="#">Исходные файлы</a>    </li>
    <li>
      <a href="/diagram/about">Диаграммы</a>    </li>
    <li>
      <a href="#">Меню 1</a>    </li>
    <li>
      <a href="#">Меню 2</a>    </li>
    <li>
      <a href="#">Меню 3</a>    </li>
  </ul>

  <h2>Меню администратора</h2>
  <ul>
    <li>
      <a href="/user/dropBox">DropBox</a>    </li>
    <li>
      <a href="/user/parse">Parse File</a>    </li>
    <li>
      <a href="#">Меню 3</a>    </li>
    <li>
      <a href="#">Меню 4</a>    </li>
    <li>
      <a href="#">Меню 5</a>    </li>
  </ul>
  <form enctype="multipart/form-data" action="/uploadFile" method="post">
    File to upload: <input type="file" name="file"><br /><br/>
    <%--Name: <input type="text" name="name"><br /> <br />--%>
    <input type="submit" value="Отправить">
  </form>

</div>