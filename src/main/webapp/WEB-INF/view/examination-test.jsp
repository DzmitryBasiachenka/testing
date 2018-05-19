<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.testing" var="testing"/>
<fmt:message key="t.question" var="questionCommon"/>
<fmt:message key="t.cancel" var="cancel"/>
<fmt:message key="t.next" var="next"/>

<s:html title="${testing}">
  <c:set var="i" scope="request" value="${0}" />
  <div class="conteiner">

    <div class="row">
      <div class="col-3"></div>
      <div class="col-6 m-3"><h2 class="text-center">${examinationSession.testName}</h2></div>
      <div class="col-3"></div>
    </div>

    <div class="row">
      <div class="col-3"></div>

      <div class="col-6 border border-secondary rounded pt-3">
        <h5>${questionCommon}: <h2 class="text-secondary">${examinationSession.question.questionName}</h2></h5>
        <hr>
        <form action="<c:url value='/examination/question/${examinationSession.question.id}'/>" method="POST">
        <c:forEach var="answer" items="${examinationSession.question.answers}">
          <div class="form-check">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input type="checkbox" class="form-check-input mt-2" name="checkbox${i=i+1}" name="index">
            <label for="check">
              ${answer.answerName}
            </label>
          </div>
        </c:forEach>
          <div class="row">
            <div class="col text-right mt-2 mb-3">
              <a class="btn btn-secondary mr-3" href="<c:url value='/examination/exit'/>" role="button">${cancel}</a>
              <button type="submit" class="btn btn-dark">${next}</button>
            </div>
          </div>
        </form>
      </div>

      <div class="col-3"></div>
    </div>

  </div>
</s:html>
