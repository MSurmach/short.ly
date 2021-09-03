<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<html>
<head>
    <link rel="stylesheet" href="../styles/home.css">
    <script src="../js/home.js"></script>
    <title>Short.ly</title>
</head>
<body>
<div class="main">
    <div class="row card">
        <div class="col-1-2">
            <span class="attractive-text">Short.ly</span>
        </div>
        <div class="col-1-2" id="auth-panel">
            <security:authorize access="!isAuthenticated()">
                <div id="panel-anonymous">
                    <input type="button" value="Login" class="button" onclick="location.href='/login'">
                    <input type="button" value="Sign Up" class="button" onclick="location.href='/signup'">
                </div>
            </security:authorize>
            <security:authorize access="isAuthenticated()">
                <security:authentication property="principal.username" var="username"/>
                <div id="panel-registered">
                    <span class="small-attractive-text">You logged as: </span>
                    <input type="button" value="${username}" class="button"
                        <%--onclick="location.href='/profile/${username}'--%>">
                    <input type="button" value="My links" class="button" onclick="location.href='/profile/links'">
                    <input type="button" value="Log out" class="button" onclick="location.href='/logout'">
                </div>
            </security:authorize>
        </div>
    </div>
    <div class="row card">
        <div id="main-input-form">
            <input type="text" id="original-link-input"
                   placeholder="Enter a long URL to make a Short.ly link">
            <input type="button" value="Shorten" class="button" onclick="generateShortLink()">
        </div>
        <div id="short-link-place" hidden>
            <span class="small-attractive-text">Your short link is here: </span>
            <span id="short-link-result"></span>
        </div>
    </div>
    <c:if test="${!empty truncatedList}">
        <div class="card" id="all-user-link-place">
            <div class="row">
                <span class="small-attractive-text">Records per page: </span>
                <c:set var="availableChoises" value="${['5','10','20']}"/>
                <select id="record-count" onchange="reloadRecords(${page},this)">
                    <c:forEach var="choice" items="${availableChoises}">
                        <option ${choice==records?'selected' : ' '}>${choice}</option>
                    </c:forEach>
                </select>
            </div>
            <c:forEach var="link" items="${truncatedList}">
                <div class="card">
                    <div class="row">
                        <span class="small-attractive-text">Short link: </span>
                        <a class="attractive-link" href="${link.shortLink}">${link.shortLink}</a>
                    </div>
                    <div class="row rename-panel" hidden>
                        <span class="small-attractive-text">Please rename the link and confirm it: </span>
                        <input type="text" class="rename-input" value="${link.shortname}">
                        <input type="button" class="button reject-rename-btn" value="Cancel" onclick="rejectRename()">
                        <input type="button" class="button submit-rename-btn" value="Confirm"
                               onclick="submitRenaming()">
                    </div>
                    <div class="row">
                        <span class="small-attractive-text">Original link: </span>
                        <span>${link.original}</span>
                    </div>
                    <div class="row">
                        <span class="small-attractive-text">Count of redirects: </span>
                        <span>${link.count}</span>
                    </div>
                    <div class="row controls">
                        <input type="button" class="button" value="Visit"
                               onclick="location.href='${link.shortLink}'">
                        <input type="button" class="button" value="Copy"
                               onclick=copyToClipboard('${link.shortLink}')>
                        <input type="button" class="button" value="Rename"
                               onclick=editLinkName("${link.shortname}")>
                        <input type="button" class="button" value="Delete" onclick=deleteLink("${link.shortname}")>
                    </div>
                </div>
            </c:forEach>
            <div class="row">
                <div class="pagination">
                    <span class="small-attractive-text">Pages: </span>
                    <c:forEach begin="1" end="${pagesCount+1}" step="1" varStatus="counter">
                        <a href="/profile/links?page=${counter.index}&records=${records}">${counter.index}</a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>
