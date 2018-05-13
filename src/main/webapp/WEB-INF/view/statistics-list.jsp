<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<s:html title="Statistics">
  <div class="container-fluide">

      <div class="col mt-1">
        <h2 class="text-center pb-1">Статистика по пройденным тестам</h2>
        <table class="table table-striped">
          <thead class="thead-dark">
            <tr>
              <th scope="col">№</th>
              <th scope="col">Название теста</th>
              <th scope="col">Предмет</th>
              <th scope="col">Количество верных ответов</th>
              <th scope="col">Количество неправильных ответов</th>
              <th scope="col">Начало тестирования</th>
              <th scope="col">Окончание тестирования</th>
            </tr>
          </thead>
          <tbody>
            <c:set var="i" scope="request" value="${0}" />
            <c:forEach var="statistics" items="${statisticsList}">
              <tr>
                  <th scope="row"><c:out value="${i=i+1}" /></th>
                  <td>${statistics.test.testName}</td>
                  <td>${statistics.test.subject.subjectName}</td>
                  <td class="text-center">${statistics.countCorrectAnswers}</td>
                  <td class="text-center">${statistics.countIncorrectAnswers}</td>
                  <td>${statistics.startTesting}</td>
                  <td>${statistics.finishTesting}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>

  </div>
</s:html>
