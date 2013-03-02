<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="spring.data.jpa.example.title"/></title>
    <link rel="stylesheet" href="/static/css/styles.css" type="text/css"/>
</head>
<body>
<div class="messages">
    <c:if test="${feedbackMessage != null}">
        <div class="messageblock"><c:out value="${feedbackMessage}"/></div>
    </c:if>
    <c:if test="${errorMessage != null}">
        <div class="errorblock"><c:out value="${errorMessage}"/></div>
    </c:if>
</div>
<h1><spring:message code="user.list.page.title"/></h1>
<a href="/user/create"><spring:message code="user.create.link.label"/></a>
<table>
    <thead>
    <tr>
        <td><spring:message code="user.label.lastName"/></td>
        <td><spring:message code="user.label.firstName"/></td>
        <td><spring:message code="user.label.email"/></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><a href="/user/edit/<c:out value="${user.id}"/>"><spring:message code="user.edit.link.label"/></a></td>
            <td><a href="/user/delete/<c:out value="${user.id}"/>"><spring:message code="user.delete.link.label"/></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>