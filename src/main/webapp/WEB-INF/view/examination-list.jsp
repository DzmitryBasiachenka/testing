<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.test.list" var="testList"/>
<fmt:message key="t.tests.on.topic" var="testsOnTopic"/>
<fmt:message key="t.test.name" var="testName"/>
<fmt:message key="t.count.questions" var="countQuestions"/>
<fmt:message key="t.author" var="author"/>

<s:html title="${testList}">
    <h2 class="text-center">${testsOnTopic} <label class="text-primary">${subjectName}</label></h2>
    <div class="row">
      <div class="col-1"></div>

      <div class="col-10">
        <table class="table table-striped" style="table-layout: fixed;">
          <thead class="thead-dark">
            <tr class="align-middle text-center">
              <th scope="col" width="5%">№</th>
              <th scope="col" width="40%">${testName}</th>
              <th scope="col" width="20%">${countQuestions}</th>
              <th scope="col" width="35%">${author}</th>
            </tr>
          </thead>
          <tbody class="table-bordered">
            <c:set var="i" scope="request" value="${0}" />
            <c:forEach var="test" items="${testsBySubjectName}">
              <tr>
                <th scope="row" class="text-center align-middle"><c:out value="${i=i+1}" /></th>
                <td class="align-middle"><a href="<c:url value='/examination/test/${test.id}'/>" role="button">${test.testName}</a></td>
                <td class="text-center align-middle">${test.questions.size()}</td>
                <td class="align-middle">${test.user.firstName}&nbsp;${test.user.lastName}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>

      <div class="col-1"></div>
    </div>
</s:html>
