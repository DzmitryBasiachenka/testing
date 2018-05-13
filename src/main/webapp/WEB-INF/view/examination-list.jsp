<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<s:html title="Examination list">
    <h2 class="text-center">Тесты на тему <label class="text-primary">${subjectName}</label></h2>
    <div class="row">
      <div class="col-1"></div>

      <div class="col-10">
        <table class="table table-striped">
          <thead class="thead-dark">
            <tr>
              <th scope="col">№</th>
              <th scope="col">Название теста</th>
              <th scope="col">Количество вопросов</th>
              <th scope="col">Автор</th>
            </tr>
          </thead>
          <tbody>
            <c:set var="i" scope="request" value="${0}" />
            <c:forEach var="test" items="${testsBySubjectName}">
              <tr>
                <th scope="row"><c:out value="${i=i+1}" /></th>
                  <td><a href="<c:url value='/examination/test/${test.id}'/>" role="button">${test.testName}</a></td>
                  <td>${test.questions.size()}</td>
                  <td>${test.user.firstName}&nbsp;${test.user.lastName}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>

      <div class="col-1"></div>
    </div>
</s:html>
