<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.menu.subject" var="subjectCommon"/>
<fmt:message key="t.add.subject" var="addSubject"/>
<fmt:message key="t.subject.list" var="subjectList"/>

<s:html title="${subjectCommon}">
  <div class="container-fluide">
    <div class="row">
      <div class="col-4"></div>

      <div class="col-4">
        <ul class="list-group">
          <button class="btn btn-dark" type="button" data-toggle="modal" data-target="#subjectAdd" data-whatever="@mdo">
            <h4>${addSubject}</h4>
          </button>
          <h3 class="card-header">${subjectList}:</h3>
          <c:set var="i" scope="request" value="${0}" />
          <c:forEach var="subject" items="${subjects}">
            <li class="list-group-item align-middle" data-toggle="tooltip" data-placement="right" title="${subject.subjectName}">
              <h5 class="alert-link"><c:out value="${i=i+1}" />.&nbsp;${subject.subjectName}</h5>
            </li>
          </c:forEach>
        </ul>
      </div>

      <div class="col-4"></div>
    </div>
  </div>
</s:html>
