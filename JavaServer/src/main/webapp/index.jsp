<%--
  Created by IntelliJ IDEA.
  User: bud_l
  Date: 29-May-19
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>


    <style>
      textarea{
        height: 200px;
        width: 200px;
      }
    </style>
  </head>
  <body>
    <form action="Comments" method="post">
      Comment:<br>
      <textarea name="message"></textarea><br>
      Email:<br>
      <input name="email" type="text"><br>
      <input type="submit" name="Submit">
    </form>
    <div>
      <jsp:include page="/Comments" />
    </div>
  </body>
</html>
