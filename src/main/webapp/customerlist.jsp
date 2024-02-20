<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 16/02/2024
  Time: 5:44 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1><a href="/customers?action=create">Add new Customer</a></h1>
<form action="/customers" method="post">
    <input name="action" value="find" hidden="">
    <input name="name">
    <button type="submit">Tim kiem</button>
</form>

<h1>Danh sach khach hang</h1>

<table  border="1px">
    <tr>
        <td>Id</td>
        <td>Ten</td>
        <td>Ngay sinh</td>
        <td>Dia chi</td>
        <td>Province</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <c:forEach var="customer" items="${kh}">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td>${customer.email}</td>
            <td>${customer.address}</td>
            <td>${customer.oProvince.nameProvince}</td>
            <td>
                <a href="/customers?action=update&id=${customer.id}">Edit</a>
            </td>
            <td><a href="/customers?action=delete&name=${customer.name}">Delete</a></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
