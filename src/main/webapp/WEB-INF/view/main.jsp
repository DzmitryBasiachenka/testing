<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<fmt:setBundle basename="messages"/>
<!--<fmt:setLocale value="!!!ru!!!_!!!RU!!!" scope="session" />-->
<fmt:message key="t.menu.main" var="main"/>
<fmt:message key="t.welcome" var="welcome"/>
<fmt:message key="t.previous" var="previous"/>
<fmt:message key="t.next" var="next"/>

<s:html title="${main}">

  <div class="alert alert-secondary mb-0" role="alert">
    <h4 class="text-center">${welcome}</h4>
  </div>

  <div class="text-center">
    <img src="<c:url value='/pictures/man.png' />" class="item-img">
  </div>

</s:html>
