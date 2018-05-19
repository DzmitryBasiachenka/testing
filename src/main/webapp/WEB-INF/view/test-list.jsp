<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.menu.test" var="testMenu"/>
<fmt:message key="t.add.test" var="addTest"/>
<fmt:message key="t.test.list" var="testList"/>
<fmt:message key="t.edit" var="edit"/>
<fmt:message key="t.delete" var="delete"/>

<s:html title="${testMenu}">
  <div class="row">

    <nav class="col-5 bg-light" id="scrollspyTest">
      <ul class="list-group list-position scrollbar-test">
        <a type="button" class="btn btn-dark" data-toggle="modal" data-target="#testAdd" data-whatever="@mdo"><h4 style="color:white;">${addTest}</h4></a>
        <h4 class="card-header">${testList}:</h4>
        <table class="table border-right border-left" style="table-layout: fixed;">
          <tbody>
            <c:set var="i" scope="request" value="${0}" />
            <c:forEach var="test" items="${tests}">
              <tr>
                <td width="80%">
                  <a class="list-group-item text-dark alert-link" data-toggle="tooltip" data-placement="top" title="${test.testName}" href="#${test.id}">
                    <c:out value="${i=i+1}" />.&nbsp;${test.testName}
                  </a>
                </td>
                <td width="10%" class="align-middle">
                  <a class="btn btn-light p-2" data-toggle="tooltip" data-placement="top" title="${edit}" href="<c:url value='/test/${test.id}'/>" role="button">
                    <i class="far fa-edit"></i>
                  </a>
                </td>
                <td width="10%" class="align-middle">
                  <a class="btn btn-light p-2" data-toggle="tooltip" data-placement="top" title="${delete}" href="<c:url value='/test/delete/${test.id}'/>" role="button">
                    <i class="far fa-trash-alt"></i>
                  </a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </ul>
    </nav>

    <div class="col-7 bg-light">
      <c:set var="numberTest" scope="session" value="${0}" />
      <c:forEach var="test" items="${tests}">
        <div id="${test.id}">
          <h3 class="alert alert-secondary text-center"><c:out value="${numberTest=numberTest+1}" />.&nbsp;${test.testName}</h3>
          <h4>${test.subject.subjectName}</h4>
          <c:set var="numberQuestion" scope="session" value="${0}" />
          <c:forEach var="question" items="${test.questions}">
            <h5><c:out value="${numberQuestion=numberQuestion+1}" />.&nbsp;${question.questionName}</h5>
            <c:forEach var="answer" items="${question.answers}">
              <div class="form-check">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <c:if test="${answer.correctAnswer == true}">
                  <input class="form-check-input" type="radio" id="check" checked>
                  <label class="text-success" for="check">
                </c:if>
                <c:if test="${answer.correctAnswer == false}">
                  <input class="form-check-input" type="radio" disabled>
                  <label>
                </c:if>
                ${answer.answerName}</label>
                <br>
              </div>
            </c:forEach>
            <br>
          </c:forEach>
        </div>
        <hr>
      </c:forEach>
    </div>

  </div>
</s:html>
