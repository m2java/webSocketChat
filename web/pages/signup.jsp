<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Create an account</title>
    <link href="<c:url value="./resources/theme1/css/style.css" />" rel="stylesheet">
</head>
<body>
<div class="login-page">
    <div class="form">
        ${requestScope.errorMessage}
        <form action="signup" method="POST" class="login-form">
            <input type="text" placeholder="username" name="login"/>
            <input type="password" placeholder="password" name="password"/>
            <button>Create</button>
            <p class="message">
                Already registered? <a href="/login">Log in</a>
            </p>
        </form>
    </div>
</div>
</body>
</html>
