<%--
  Created by IntelliJ IDEA.
  User: SMULL
  Date: 5/11/2015
  Time: 3:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page session="false" %>
<html>
<head>
    <title>Parse file</title>
    <link rel="stylesheet" href="/css/parseFile/parseFile.css" type="text/css">
    <script src="/js/jquery-1.11.2.js"></script>
    <script src="/js/jquery-ui-1.11.2/jquery-ui.js"></script>
    <script src="/js/parseFile.js"></script>
</head>
<body>


    <div id="dialog_window_1" class="dialog_window" title="Announcement" style="display: none" >
        <p>File is added to parse</p>
    </div>
    <h2 style="margin-left: 20px">List of files to server</h2>
    <table class="bordered">
        <thead>
        <tr>
            <th> Name</th>
        </tr>
        </thead>
        <tbody class="collection">
        <c:forEach var="file" items="${serverFiles}">
            <tr>
                <td><c:if test="${not empty file}">${file}</c:if></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</body>
</html>
