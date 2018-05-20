<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.menu.statistics" var="statisticsCommon"/>
<fmt:message key="t.menu.test" var="testMenu"/>
<fmt:message key="t.student.statistics" var="studentStatistics"/>
<fmt:message key="t.subject" var="subjectCommon"/>
<fmt:message key="t.login" var="loginCommon"/>
<fmt:message key="t.first.name" var="firstNameCommon"/>
<fmt:message key="t.last.name" var="lastNameCommon"/>
<fmt:message key="t.count.correct.answers" var="countCorrectAnswersCommon"/>
<fmt:message key="t.count.incorrect.answers" var="countIncorrectAnswersCommon"/>
<fmt:message key="t.start.testing" var="startTestingCommon"/>
<fmt:message key="t.finish.testing" var="finishTestingCommon"/>
<fmt:message key="t.delete" var="delete"/>

<s:html title="${statisticsCommon}">
  <div class="col mt-1">
    <c:forEach var="statistics" items="${statisticsList}">
      <c:set var="id" scope="request" value="${statistics.test.id}" />
      <c:set var="testName" scope="request" value="${statistics.test.testName}" />
      <c:set var="subjectName" scope="request" value="${statistics.test.subject.subjectName}" />
      <break>
    </c:forEach>
    <h2 class="text-center pb-1">${studentStatistics}. ${testMenu}: ${testName}. ${subjectCommon}: ${subjectName}.</h2>

    <table class="table table-striped" style="table-layout: fixed;">

      <thead class="thead-dark">
        <tr>
          <th scope="col" class="align-middle text-center" width="4%">№</th>
          <th scope="col" class="align-middle text-center" width="12%">${loginCommon}</th>
          <th scope="col" class="align-middle text-center" width="12%">${firstNameCommon}</th>
          <th scope="col" class="align-middle text-center" width="12%">${lastNameCommon}</th>

          <th scope="col" class="align-middle text-center" width="15%">${countCorrectAnswersCommon}</th>
          <th scope="col" class="align-middle text-center" width="15%">${countIncorrectAnswersCommon}</th>
          <th scope="col" class="align-middle text-center" width="13%">${startTestingCommon}</th>
          <th scope="col" class="align-middle text-center" width="13%">${finishTestingCommon}</th>
          <th scope="col" class="align-middle text-center" width="4%"></th>
        </tr>
      </thead>

      <tbody class="table-bordered">
        <c:set var="i" scope="request" value="${0}" />
        <c:forEach var="statistics" items="${statisticsList}">
          <tr>
            <th class="align-middle text-center" scope="row"><c:out value="${i=i+1}" /></th>
            <td class="align-middle">${statistics.user.login}   </td>
            <td class="align-middle">${statistics.user.firstName}</td>
            <td class="align-middle">${statistics.user.lastName}</td>
            <td class="align-middle text-center">${statistics.countCorrectAnswers}</td>
            <td class="align-middle text-center">${statistics.countIncorrectAnswers}</td>
            <td class="align-middle text-center">${statistics.startTesting}</td>
            <td class="align-middle text-center">${statistics.finishTesting}</td>
            <td class="align-middle">

            <form action="<c:url value='/statistics/delete/${statistics.id}'/>" method="POST">
               <button type="submit" class="btn btn-light p-2" data-toggle="tooltip" data-placement="top" title="${delete}" name="testId" value="${id}">
                 <i class="fas fa-trash-alt"></i>
               </button>
            </form>

            </td>
          </tr>
        </c:forEach>
      </tbody>

    </table>
  </div>

</s:html>
