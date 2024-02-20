<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 16/02/2024
  Time: 6:35 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form Create New Customer</title>
</head>
<body>
<form method="post">
    <div>
        <label>Name</label>
        <input type="text" name="name" placeholder="Name">
    </div>
    <div>
        <label>Email</label>
        <input type="text" name="email" placeholder="Email">
    </div>
    <div>
    <label>Address</label>
    <input type="text" name="address" placeholder="Address">
    </div>
    <div>
        <label>Lớp</label>
        <select name="idProvince">
            <c:forEach items="${province}" var="p">
                <option value="${p.idProvince}">${p.nameProvince}</option>
            </c:forEach>
        </select>
    </div>
    <button>Create</button>
</form>
</body>
</html>
