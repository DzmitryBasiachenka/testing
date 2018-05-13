<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<s:html title="Statistics">
  <div class="col mt-1">
    <h2 class="text-center pb-1">Статистика по пройденным тестам</h2>
    <table class="table table-striped" style="table-layout: fixed;">

      <thead class="thead-dark">
        <tr>
          <th scope="col" class="align-middle" width="5%">№</th>
          <th scope="col" class="align-middle" width="20%">Название теста</th>
          <th scope="col" class="align-middle" width="10%">Предмет</th>
          <th scope="col" class="align-middle" width="15%">Количество верных ответов</th>
          <th scope="col" class="align-middle" width="15%">Количество неверных ответов</th>
          <th scope="col" class="align-middle" width="15%">Начало тестирования</th>
          <th scope="col" class="align-middle" width="15%">Окончание тестирования</th>
        </tr>
      </thead>

      <tbody>
        <c:set var="i" scope="request" value="${0}" />
        <c:forEach var="statistics" items="${statisticsList}">
          <tr>
            <th class="align-middle" scope="row"><c:out value="${i=i+1}" /></th>
            <td class="align-middle"><h6>${statistics.test.testName}</h6></td>
            <td class="align-middle">${statistics.test.subject.subjectName}</td>
            <td class="align-middle text-center">${statistics.countCorrectAnswers}</td>
            <td class="align-middle text-center">${statistics.countIncorrectAnswers}</td>
            <td class="align-middle">${statistics.startTesting}</td>
            <td class="align-middle">${statistics.finishTesting}</td>
          </tr>
        </c:forEach>
      </tbody>

    </table>
  </div>
</s:html>
