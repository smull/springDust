<%--
  Created by IntelliJ IDEA.
  User: SMULL
  Date: 5/7/2015
  Time: 8:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page session="false" %>
<html>
<head>
    <title>DropBox</title>
    <link rel="stylesheet" href="/css/dropBox/dropBox.css" type="text/css">
    <script src="/js/jquery-1.11.2.js"></script>
    <script src="/js/jquery-ui-1.11.2/jquery-ui.js"></script>
    <script src="/js/dropBox.js"></script>

</head>
<body>

        <div id="dialog_window_1" class="dialog_window" title="Announcement" style="display: none" >
            <p>File is downloaded</p>
        </div>
        <%--<c:out value="${authorizeUrl}"></c:out>--%>
        <h2 style="margin-left: 20px">List of DropBox Files</h2>
        <table class="bordered">
            <thead>
                <tr>
                    <th> Name</th>
                    <th> Date changed</th>
                    <th> Size</th>
                </tr>
            </thead>
            <tbody class="collection">
                <c:forEach var="file" items="${dropBox}">
                    <tr>
                        <td><c:if test="${not empty file.name}">${file.name}</c:if></td>
                        <td >${file.lastModified}</td>
                        <td>${file.numBytes}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>



         <%--<c:if test="${not empty authorizeUrl}"> src="${authorizeUrl}"></c:if>/>...</iframe>--%>
</body>
</html>
