<%--
  Created by IntelliJ IDEA.
  User: SMULL
  Date: 5/29/2015
  Time: 11:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dust</title>
    <script src="/js/dust-full.js"></script>
    <script>
        $(document).ready(function(){
           dust.isDebug = true;
            var template = $("#userTemplate").html();
            var compiled = dust.compile(template,"user_template");
            dust.loadSource(compiled);
            $("#renderButton").click(function(){
                $.ajax({
                   url: "/user/dust",
                   type: "GET",
                   dataType: "json",
                   success: function(data){
                       dust.render("user_template",data,function(err,out){
                           console.log(out);
                           $(".content").html(out);
                       })
                   }
                });
            });


        });


    </script>
</head>
<body>
<script id="userTemplate" type="test/dust">
    <div>
        <div>Hello dear {name}!</div>
        <div>E-mail: {email}</div>
        <div>Password: {password}</div>
    </div>

</script>

<div class="content"></div>
<input type="button" value="Test" id="renderButton">
</body>
</html>
