<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<s:html title="Question">
  <div class="container">
    <div class="row">
      <div class="col-1"></div>

      <div class="col-10 border border-secondary bg-light pt-3">
        <h4 class="text-center pb-2">Редактировать вопрос</h4>
        <form action="<c:url value='/question/edit/${question.id}'/>" method="POST">
          <div class="form-group row">
            <label class="col-2 col-form-label">Вопрос</label>
            <div class="col-10">
              <input type="text" class="form-control" name="questionName" value="${question.questionName}" required>
              <div class="invalid-feedback">
                Please choose question.
              </div>
            </div>
          </div>

          <div class="form-group row">
            <label class="col-2 col-form-label">Ответы</label>
            <div class="col-10">
              <c:set var="i" scope="request" value="${0}" />
              <c:forEach var="answer" items="${question.answers}">
                <div class="form-check">
                  <c:if test="${answer.correctAnswer == true}">
                    <input type="checkbox" class="form-check-input mt-2" data-toggle="tooltip" data-placement="left" title="Правильный ответ" name="checkbox<c:out value="${i=i+1}" />" checked>
                  </c:if>
                  <c:if test="${answer.correctAnswer == false}">
                    <input type="checkbox" class="form-check-input mt-2" data-toggle="tooltip" data-placement="left" title="Правильный ответ" name="checkbox<c:out value="${i=i+1}" />">
                  </c:if>
                  <input type="text" class="form-control mt-2" name="answer${i}" value="${answer.answerName}" maxlength="256" required>
                  <div class="invalid-feedback">
                    Please choose answer.
                  </div>
                </div>
              </c:forEach>
            </div>
          </div>

          <div class="row">
            <div class="col text-right mb-3">
              <a class="btn btn-secondary mr-sm-3" href="<c:url value='/test/list'/>" role="button">Отмена</a>
              <button type="submit" class="btn btn-dark">Сохранить</button>
            </div>
          </div>

        </form>
      </div>

      <div class="col-1"></div>
    </div>
  </div>
</s:html>
