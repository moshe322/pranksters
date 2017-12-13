<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">

<head>
    <title>Games</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webapps/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webapps/lib/css/games.css">
    <script src="${pageContext.request.contextPath}/webapps/bootstrap/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
</head>

<body>
<header class="container-fluid row">
    <img src="${pageContext.request.contextPath}/webapps/ext/img/games/games.png" class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
</header>

<div id = "legend" class="container-fluid row">
    <p>On this page you can get acquainted with the rules of the games available in this community.</p>
</div>


<div id = "contentBox" class="container-fluid row">

    <div id = "nav" class="col-xs-3 col-sm-3 col-md-3 col-lg-2 ">
        <h2>Navigation</h2>
        <ul>
            <c:forEach var="item" items = "${requestScope.gamesList}">
                <li><a href="#${item.name}">${item.name}</a></li>
            </c:forEach>
        </ul>

    </div>

    <div id = "content" class="col-xs-8 col-sm-8 col-md-8 col-lg-9">
        <c:forEach var="item" items = "${requestScope.gamesList}">
            <jsp:include page= "/webapps/views/game.jsp">
                <jsp:param name="name" value="${item.name}"/>
                <jsp:param name="description" value="${item.shortDescription}"/>
                <jsp:param name="ico" value="${item.ico}"/>
            </jsp:include>
        </c:forEach>
    </div>

</div>

<footer>

</footer>

</body>

</html>