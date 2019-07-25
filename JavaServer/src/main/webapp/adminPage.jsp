<%--
  Created by IntelliJ IDEA.
  User: bud_l
  Date: 03-Jun-19
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="jquery.min.js"></script>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        #probeTable tr:hover{
            background-color: #f5f5f5;
        }
    </style>
</head>

<body>
    <table id="commentsTable">
        <tr>
            <td>ID</td><td>Email</td><td>Mesaj</td>
        </tr>
        <jsp:include page="AdminPage"></jsp:include>
    </table>

    <form action="AdminPage" method="post">

        <input id="id" name="id" type="text">
        <input type="submit" name="verify" >
    </form>

    <script>
        setTimeout(function () {
            $("#commentsTable").find("tr").each(function () {
                $(this).click(function () {
                    $("#id").val($(this).children(":nth-child(1)").html());
                })
            })
        },1000);

    </script>
</body>
</html>
