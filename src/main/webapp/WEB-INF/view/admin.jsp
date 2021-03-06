<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.menu.administrator" var="administrator"/>
<fmt:message key="t.user.list" var="userList"/>
<fmt:message key="t.subject.list" var="subjectList"/>

<s:html title="${administrator}">
<div class="row p-5"></div>
  <div class="row p-5">
    <div class="col-1"></div>
    <div class="col-5 text-center">
      <h2 class="text-center">${userList}</h2>
      <a class="btn btn-light p-3" data-toggle="tooltip" data-placement="top" title="User list" href="<c:url value='/admin/user/list'/>" role="button">
        <i class="fas fa-users fa-9x"></i>
      </a>
    </div>
    <div class="col-5 text-center">
      <h2 class="text-center">${subjectList}</h2>
      <a class="btn btn-light p-3" data-toggle="tooltip" data-placement="top" title="Subject list" href="<c:url value='/admin/subject/list'/>" role="button">
        <i class="fas fa-list-ol fa-9x"></i>
      </a>
    </div>
    <div class="col-1"></div>
  </div>
</s:html>
