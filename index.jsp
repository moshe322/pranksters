<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">

<head>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <!-- <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script> -->
        <!--<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script> -->
    <!--[endif]-->

    <title>Friday Pranksters Main Page</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/webapps/ext/img/games/favicon.png" type="image/png">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webapps/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webapps/lib/css/index.css">
    <script src="${pageContext.request.contextPath}/webapps/bootstrap/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
</head>

<body>

<header class="container-fluid row">
    <img src="${pageContext.request.contextPath}/webapps/ext/img/index/index.png" class="col-xs-8 col-xs-offset-2 col-sm-8 col-sm-offset-2 col-md-8 col-md-offset-2 col-lg-5 col-lg-offset-4">
</header>

<div id="navigation" class="container-fluid row">

    <div id="freeplace" class="col-lg-2">
        <c:if test = "${sessionScope.user != null}" >
            <jsp:include page= "/webapps/views/logedInWindow.jsp">
                <jsp:param name="username" value="${sessionScope.user.name}" />
                <jsp:param name="surname" value="${sessionScope.user.surname}" />
            </jsp:include>
        </c:if>
    </div>

    <nav id="navpanel" class="col-lg-4 col-lg-offset-2">
        <ul>
            <li><a href="${pageContext.request.contextPath}/loadGames">Games</a></li>
            <li><a>Community</a></li>
            <li><a>Hall of Fame</a></li>
            <li><a>About us</a></li>
        </ul>
    </nav>

    <div id="authorization" class="col-lg-2 col-lg-offset-2">
        <ul>
            <li><a href="${pageContext.request.contextPath}/webapps/views/login.jsp">Login</a></li>
            <li><a href="${pageContext.request.contextPath}/webapps/views/registration.jsp">Registration</a></li>
        </ul>
    </div>

</div>

<p>${status}</p>

<footer>

</footer>

</body>

</html>