<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.menu.statistics" var="statisticsCommon"/>
<fmt:message key="t.statistics.passed.tests" var="statisticsPassedTests"/>
<fmt:message key="t.test.name" var="testNameCommon"/>
<fmt:message key="t.subject" var="subjectCommon"/>
<fmt:message key="t.count.correct.answers" var="countCorrectAnswersCommon"/>
<fmt:message key="t.count.incorrect.answers" var="countIncorrectAnswersCommon"/>
<fmt:message key="t.start.testing" var="startTestingCommon"/>
<fmt:message key="t.finish.testing" var="finishTestingCommon"/>

<s:html title="${statisticsCommon}">
  <div class="col mt-1">
    <h2 class="text-center pb-1">${statisticsPassedTests}</h2>
    <table class="table table-striped" style="table-layout: fixed;">

      <thead class="thead-dark">
        <tr>
          <th scope="col" class="align-middle text-center" width="4%">№</th>
          <th scope="col" class="align-middle text-center" width="20%">${testNameCommon}</th>
          <th scope="col" class="align-middle text-center" width="10%">${subjectCommon}</th>
          <th scope="col" class="align-middle text-center" width="14%">${countCorrectAnswersCommon}</th>
          <th scope="col" class="align-middle text-center" width="14%">${countIncorrectAnswersCommon}</th>
          <th scope="col" class="align-middle text-center" width="14%">${startTestingCommon}</th>
          <th scope="col" class="align-middle text-center" width="15%">${finishTestingCommon}</th>
        </tr>
      </thead>

      <tbody class="table-bordered">
        <c:set var="i" scope="request" value="${0}" />
        <c:forEach var="statistics" items="${statisticsList}">
          <tr>
            <th class="align-middle text-center" scope="row"><c:out value="${i=i+1}" /></th>
            <td class="align-middle">${statistics.test.testName}</td>
            <td class="align-middle">${statistics.test.subject.subjectName}</td>
            <td class="align-middle text-center">${statistics.countCorrectAnswers}</td>
            <td class="align-middle text-center">${statistics.countIncorrectAnswers}</td>
            <td class="align-middle text-center">${statistics.startTesting}</td>
            <td class="align-middle text-center">${statistics.finishTesting}</td>
          </tr>
        </c:forEach>
      </tbody>

    </table>
  </div>
</s:html>
