<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.menu.about" var="about"/>
<fmt:message key="t.about.description" var="aboutDescription"/>
<fmt:message key="t.version" var="version"/>

<s:html title="${about}">
 <div class="jumbotron">
   <h1 class="display-7">${about}</h1>
   <hr>
   <p class="lead">${aboutDescription}</p>
   <hr>
   <p>${version} v1.0/2018</p>
 </div>
</s:html>
