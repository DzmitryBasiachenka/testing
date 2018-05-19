<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.error.404" var="error404"/>
<fmt:message key="t.error.404.message" var="errorMessage"/>

<s:html title="${error404}">
  <div class="alert alert-dark text-center" role="alert">
    <h1 class="text-danger">${error404}</h1>
    <p><h4>${errorMessage}</h4></p>
  </div>
</s:html>
