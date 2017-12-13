<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">

<head>
    <title>Friday Pranksters Test Page</title>
    <meta charset="utf-8">
</head>

<body>

<c:forEach var="item" items = "${requestScope.gamesList}">
    <p>id: ${item.id}</p>
    <p>name: ${item.name}</p>
    <p>short: ${item.shortDescription}</p>
    <p>full: ${item.fullDescription}</p>
    <p>ico: ${item.ico}</p>
    <p>images: ${item.images}</p>
    <p>rate: ${item.rate}</p>
</c:forEach>

<p>Context path: ${pageContext.request.contextPath}</p>

</body>

</html>