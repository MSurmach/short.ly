<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="../styles/login.css">
</head>
<body>
<span class="attractive-text centered" id="head-line">Please login</span>
<form id="login-form" action="/login" method="post">
    <input type="text" name="username" id="username-input" placeholder="Username">
    <br>
    <input type="password" name="password" id="password-input" placeholder="Password" onkeyup="">
    <br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="submit" value="Login" class="button">
</form>
</body>
</html>