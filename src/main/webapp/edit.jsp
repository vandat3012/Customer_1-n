<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 20/02/2024
  Time: 1:38 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>

<form method="post">
     <fieldset>
    <legend>Customer information</legend>
    <table>
        <tr>
            <td>Name: </td>
            <td><input type="text" name="name" id="name" value="${requestScope["customer"].getName()}"></td>
        </tr>
        <tr>
            <td>Email: </td>
            <td><input type="text" name="email" id="email" value="${requestScope["customer"].getEmail()}"></td>
        </tr>
        <tr>
            <td>Address: </td>
            <td><input type="text" name="address" id="address" value="${requestScope["customer"].getAddress()}"></td>
        </tr>
        <tr>
            <select name="idProvince">
                <c:forEach items="${province}" var="p">
                    <option value="${p.idProvince}">${p.nameProvince}</option>
                </c:forEach>
            </select>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit"></td>
        </tr>
    </table>
</fieldset>
</form>
</body>
</html>
