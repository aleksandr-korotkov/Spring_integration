<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>продажи</title>
</head>
<body>
<div>
    <table>
        <c:forEach items="${sales_list}" var="item">
            <tr>
                <td><c:out value="${item}" /></td>
            </tr>
        </c:forEach>
    </table>
    <a href="/">Главная</a>
</div>
</body>
</html>