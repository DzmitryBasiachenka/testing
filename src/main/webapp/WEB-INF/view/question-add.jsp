<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.new.question" var="newQuestion"/>
<fmt:message key="t.question" var="questionCommon"/>
<fmt:message key="t.test.name" var="testNameCommon"/>
<fmt:message key="t.subject" var="subjectCommon"/>
<fmt:message key="t.question.placeholder" var="questionPlaceholder"/>
<fmt:message key="t.answer.placeholder" var="answerPlaceholder"/>
<fmt:message key="t.correct.answer" var="correctAnswerCommon"/>
<fmt:message key="t.cancel" var="cancel"/>
<fmt:message key="t.next" var="next"/>
<fmt:message key="t.save" var="save"/>

<s:html title="${newQuestion}">
  <div class="container">
    <div class="row">
      <div class="col-1"></div>

      <div class="col-10 border border-secondary rounded bg-light pt-3">
        <h4 class="text-primary text-center pb-2">${questionCommon} № ${testSession.questions.size() + 1}</h4>
        <form action="<c:url value='/question/add'/>" method="POST">
          <div class="form-group row">
            <label class="col-2 col-form-label">${testNameCommon}</label>
            <div class="col-10">
              <input type="text" class="form-control" placeholder="${testSession.testName}" readonly>
            </div>
          </div>

          <div class="form-group row">
            <label class="col-2 col-form-label">${subjectCommon}</label>
            <div class="col-10">
              <input type="text" class="form-control" placeholder="${testSession.subjectName}" readonly>
            </div>
          </div>

          <div class="form-group row">
            <label class="col-2 col-form-label">${questionCommon} № ${testSession.questions.size() + 1}</label>
            <div class="col-10">
              <input type="text" class="form-control mb-1" name="questionName" placeholder="${questionPlaceholder}" required>

              <div class="col-12">
                <div class="form-check">
                  <input type="checkbox" class="form-check-input mt-2" data-toggle="tooltip" data-placement="left" title="${correctAnswerCommon}" name="checkbox1">
                  <input type="text" class="form-control mt-2 ml-3" name="answer1" placeholder="${answerPlaceholder}" maxlength="256" required>
                </div>

                <div class="form-check">
                  <input type="checkbox" class="form-check-input mt-2" data-toggle="tooltip" data-placement="left" title="${correctAnswerCommon}" name="checkbox2">
                  <input type="text" class="form-control mt-1 ml-3" name="answer2" placeholder="${answerPlaceholder}" maxlength="256" required>
                </div>

                <div class="form-check">
                  <input type="checkbox" class="form-check-input mt-2" data-toggle="tooltip" data-placement="left" title="${correctAnswerCommon}" name="checkbox3">
                  <input type="text" class="form-control mt-1 ml-3" name="answer3" placeholder="${answerPlaceholder}" maxlength="256" required>
                </div>

                <div class="form-check">
                  <input type="checkbox"  class="form-check-input mt-2" data-toggle="tooltip" data-placement="left" title="${correctAnswerCommon}" name="checkbox4">
                  <input type="text" class="form-control mt-1 ml-3" name="answer4" placeholder="${answerPlaceholder}" maxlength="256" required>
                </div>
              </div>

            </div>
          </div>

          <div class="row">
            <div class="col text-right mb-3">
              <a class="btn btn-secondary mr-3" href="<c:url value='/test/exit'/>" role="button">${cancel}</a>

              <c:if test="${testSession.questions.size() < testSession.countQuestions - 1}">
                <button type="submit" class="btn btn-outline-primary">${next}</button>
              </c:if>

              <c:if test="${testSession.questions.size() == testSession.countQuestions - 1}">
                <button type="submit" class="btn btn-dark">${save}</button>
              </c:if>

            </div>
          </div>

        </form>
      </div>

      <div class="col-1"></div>
    </div>
  </div>
</s:html>
