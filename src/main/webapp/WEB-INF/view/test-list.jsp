<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<s:html title="Test">
  <div class="container-fluid">
    <div class="row">
      <div class="col-1 bg-light"></div>
        <nav class="col-4 bg-light" id="scrollspyTest">
          <ul class="list-group list-position scrollbar-test">
            <button class="btn btn-dark" type="button" data-toggle="modal" data-target="#testAdd" data-whatever="@mdo"><h4>Добавить тест</h4></button>
            <h4 class="card-header">Список тестов:</h4>
            <table class="table border-right border-left">
              <c:set var="i" scope="request" value="${0}" />
                <c:forEach var="test" items="${tests}">
                  <thead>
                    <tr>
                      <th scope="col" class="col-10">
                        <a class="list-group-item text-dark alert-link" data-toggle="tooltip" data-placement="top" title="${test.testName}" href="#${test.id}">
                          <c:out value="${i=i+1}" />.&nbsp;${test.testName}
                        </a>
                      </th>
                      <th scope="col" class="col-1 align-middle">
                        <a class="btn btn-light" data-toggle="tooltip" data-placement="top" title="Edit" href="<c:url value='/test/edit/${test.id}'/>" role="button">
                          <i class="far fa-edit"></i>
                        </a>
                      </th>
                      <th scope="col" class="col-1 align-middle">
                        <a class="btn btn-light" data-toggle="tooltip" data-placement="top" title="Delete" href="<c:url value='/test/delete/${test.id}'/>" role="button">
                          <i class="far fa-trash-alt"></i>
                        </a>
                      </th>
                    </tr>
                  </thead>
                </c:forEach>
            </table>
          </ul>
        </nav>
        <div class="col-6 bg-light">
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
                          ${answer.answerName}
                        </label>
                        <br>
                      </div>
                    </c:forEach>
                    <br>
                </c:forEach>
            </div>
            <hr>
          </c:forEach>
        </div>
      <div class="col-1 bg-light"></div>
    </div>
  </div>
</s:html>
