<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="../styles/login.css">
    <script src="../js/signup.js"></script>
</head>
<body>
<span class="attractive-text centered" id="head-line">Please sign up</span>
<form id="login-form">
    <input type="text" name="username" id="username-input" placeholder="Username">
    <br>
    <input type="password" name="password" id="password-input" placeholder="Password" onkeyup="">
    <br>
    <input type="button" value="Sign Up" class="button" onclick="signUp()">
</form>
</body>
</html>