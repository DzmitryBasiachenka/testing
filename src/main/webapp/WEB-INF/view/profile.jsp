<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.menu.profile" var="profile"/>
<fmt:message key="t.user.account" var="userAccount"/>
<fmt:message key="t.login" var="login"/>
<fmt:message key="t.email" var="email"/>
<fmt:message key="t.first.name" var="firstName"/>
<fmt:message key="t.last.name" var="lastName"/>
<fmt:message key="t.role" var="role"/>
<fmt:message key="t.delete" var="delete"/>
<fmt:message key="t.edit" var="edit"/>

<s:html title="${profile}">
  <div class="container">
    <div class="row">
      <div class="col-2"></div>

      <div class="col-8 border border-secondary rounded">
        <form class="mt-3">
          <h3 class="text-center">${userAccount} <label class="text-success">${userSession.login}</label></h3>
          <hr>

          <div class="text-center">
            <c:if test="${dataSaved != null}">
              <h5 class="text-success"><fmt:message key="${dataSaved}"/></h5>
            </c:if>

            <c:if test="${emailExists != null}">
              <h5 class="text-danger"><fmt:message key="${emailExists}"/></h5>
            </c:if>

            <c:if test="${emailWrong != null}">
              <h5 class="text-danger"><fmt:message key="${emailWrong}"/></h5>
            </c:if>

            <c:if test="${passwordsNotEquals != null}">
              <h5 class="text-danger"><fmt:message key="${passwordsNotEquals}"/></h5>
            </c:if>

            <c:if test="${userEmpty != null}">
              <h5 class="text-danger"><fmt:message key="${userEmpty}"/></h5>
            </c:if>
          </div>

          <div class="form-group">
            <label><h4>${login}</h4></label>
            <input type="text" class="form-control" value=${userSession.login} disabled>
          </div>

          <div class="form-group">
            <label><h4>${email}</h4></label>
            <input type="email" name="email" class="form-control" value=${userSession.email} disabled>
          </div>

          <div class="form-group">
            <label><h4>${firstName}</h4></label>
            <input type="text" class="form-control" value=${userSession.firstName} disabled>
          </div>

          <div class="form-group">
            <label><h4>${lastName}</h4></label>
            <input type="text" class="form-control" value=${userSession.lastName} disabled>
          </div>

          <div class="form-group">
            <label><h4>${role}</h4></label>
            <c:forEach var="role" items="${userSession.roles}">
              <input type="text" class="form-control" value=${role.roleName} disabled>
              <p></p>
            </c:forEach>
          </div>

        </form>

        <div class="row mb-3">
          <div class="col"></div>
          <div class="col"></div>
          <div class="col"></div>
          <div class="col text-right pr-0">
            <form action="<c:url value='/user/delete'/>" method="GET">
              <button class="btn btn-secondary" type="submit">${delete}</button>
            </form>
          </div>
          <div class="col text-right">
            <button class="btn btn-dark" type="button" data-toggle="modal" data-target="#userEdit" data-whatever="@mdo">${edit}</button>
          </div>
        </div>

      </div>

      <div class="col-2"></div>
    </div>
  </div>
</s:html>
