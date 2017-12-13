<!doctype html>
<html lang="en">

<head>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <!--<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>-->
    <![endif]-->
    <title>Registration</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/webapps/ext/img/games/favicon.png" type="image/png">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webapps/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webapps/lib/css/login_registration.css">
    <script src="${pageContext.request.contextPath}/webapps/bootstrap/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
</head>

<body>
<p>${status}</p>

<form action="<%=request.getContextPath() + "/registration"%>" accept-charset="UTF-8" method="post" class="container-fluid row col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 col-lg-4 col-lg-offset-4">
    <fieldset>
        <legend>Registration</legend>
        <p>
            <input type="email" name="email" placeholder = "Enter your e-mail adress" maxlength = "45" required>
        </p>
        <div class="inline">
            <input type="password" name="password" placeholder = "Enter your password" maxlength = "16" minlength="6" required>
            <input type="password" name="password2" placeholder = "Repeat your password" maxlength = "16" minlength="6" required>
        </div>
        <p>
            <input type="text" name="username" placeholder = "Name" maxlength = "40" required>
        </p>
        <p>
            <input type="text" name="surname" placeholder = "Surname" maxlength = "40" required>
        </p>
        <p>
            <input type="date" name="birthday" placeholder = "Birthday" required>
        </p>
        <p>
            <input type="text" name="country" placeholder = "Country" maxlength = "45">
        </p>
        <p>
            <input type="text" name="sity" placeholder = "Sity" maxlength = "45">
        </p>
    </fieldset>
    <div class="inline">
        <input type="submit" value="Submit" class="width20">
        <input type="reset" value="Clear" class="width20">
    </div>

</form>

</body>

</html>