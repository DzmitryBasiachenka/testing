<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.user.account" var="userAccount"/>
<fmt:message key="t.login" var="login"/>
<fmt:message key="t.email" var="email"/>
<fmt:message key="t.first.name" var="firstName"/>
<fmt:message key="t.last.name" var="lastName"/>
<fmt:message key="t.role" var="role"/>
<fmt:message key="t.delete" var="delete"/>
<fmt:message key="t.add" var="add"/>
<fmt:message key="t.cancel" var="cancel"/>

<s:html title="${user.login}">
<div class="container">
  <div class="row">
    <div class="col-2"></div>

    <div class="col-8 border border-secondary rounded pt-2">
      <h3 class="text-center">${userAccount} <label class="text-success">${user.login}</label></h3>
      <hr>

      <div class="form-group">
        <label><h4>${login}</h4></label>
        <input type="text" class="form-control" value=${user.login} disabled>
      </div>

      <div class="form-group">
        <label><h4>${email}</h4></label>
        <input type="email"class="form-control" value=${user.email} disabled>
      </div>

      <div class="form-group">
        <label><h4>${firstName}</h4></label>
        <input type="text" class="form-control" value=${user.firstName} disabled>
      </div>

      <div class="form-group">
        <label><h4>${lastName}</h4></label>
        <input type="text" class="form-control" value=${user.lastName} disabled>
      </div>

      <div class="form-group">
        <label><h4>${role}</h4></label>
        <c:forEach var="role" items="${user.roles}">

          <c:if test="${role.roleName eq 'User'}">
             <input type="text" class="form-control" value=${role.roleName} disabled>
          </c:if>

          <c:if test="${role.roleName ne 'User'}">
            <div class="row">
              <div class="col-10">
                <input type="text" class="form-control" value=${role.roleName} disabled>
              </div>
              <div class="col-2 text-center pt-1">
                <form action="<c:url value='/admin/role/delete/${role.id}'/>" method="POST">
                  <button type="submit" class="btn btn-outline-secondary btn-sm" name="login" value="${user.login}">${delete}</button>
                </form>
              </div>
            </div>
          </c:if>

          <p></p>
        </c:forEach>
      </div>

      <form action="<c:url value='/admin/role/add'/>" method="POST">
        <div class="form-group">
          <div class="row">
            <div class="col-10">
              <select class="form-control" name="roleSelect">
                <c:forEach var="role" items="${roles}">
                  <option>${role.roleName}</option>
                </c:forEach>
              </select>
            </div>
            <div class="col-2 text-center pt-1">
              <button type="submit" class="btn btn-outline-secondary btn-sm"  name="userId" value="${user.id}">${add}</button>
            </div>
          </div>
        </div>
      </form>


      <div class="row mb-3">
        <div class="col"></div>
        <div class="col"></div>
        <div class="col"></div>
        <div class="col text-right">
          <a class="btn btn-outline-secondary pr-3 mr-2 mt-1" href="<c:url value='/admin/user/list'/>" role="button">${cancel}</a>
        </div>
      </div>

    </div>
    <div class="col-2"></div>
  </div>
</div>
</s:html>
