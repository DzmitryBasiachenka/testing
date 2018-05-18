<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<s:html title="New question">
  <div class="container">
    <div class="row">
      <div class="col-1"></div>

      <div class="col-10 border border-secondary rounded bg-light pt-3">
        <h4 class="text-primary text-center pb-2">Вопрос № ${testSession.questions.size() + 1}</h4>
        <form action="<c:url value='/question/add'/>" method="POST">
          <div class="form-group row">
            <label class="col-2 col-form-label">Имя теста</label>
            <div class="col-10">
              <input type="text" class="form-control" placeholder="${testSession.testName}" readonly>
            </div>
          </div>

          <div class="form-group row">
            <label class="col-2 col-form-label">Предмет</label>
            <div class="col-10">
              <input type="text" class="form-control" placeholder="${testSession.subjectName}" readonly>
            </div>
          </div>

          <div class="form-group row">
            <label class="col-2 col-form-label">Вопрос № ${testSession.questions.size() + 1}</label>
            <div class="col-10">
              <input type="text" class="form-control mb-1" name="questionName" placeholder="Question" required>
              <div class="invalid-feedback">
                Please choose question.
              </div>

              <div class="col-12">
                <div class="form-check">
                  <input type="checkbox" class="form-check-input mt-2" data-toggle="tooltip" data-placement="left" title="Правильный ответ" name="checkbox1">
                  <input type="text" class="form-control mt-2 ml-3" name="answer1" placeholder="Answer" maxlength="256" required>
                  <div class="invalid-feedback">
                    Please choose answer.
                  </div>
                </div>

                <div class="form-check">
                  <input type="checkbox" class="form-check-input mt-2" data-toggle="tooltip" data-placement="left" title="Правильный ответ" name="checkbox2">
                  <input type="text" class="form-control mt-1 ml-3" name="answer2" placeholder="Answer" maxlength="256" required>
                  <div class="invalid-feedback">
                    Please choose answer.
                  </div>
                </div>

                <div class="form-check">
                  <input type="checkbox" class="form-check-input mt-2" data-toggle="tooltip" data-placement="left" title="Правильный ответ" name="checkbox3">
                  <input type="text" class="form-control mt-1 ml-3" name="answer3" placeholder="Answer" maxlength="256" required>
                  <div class="invalid-feedback">
                    Please choose answer.
                  </div>
                </div>

                <div class="form-check">
                  <input type="checkbox"  class="form-check-input mt-2" data-toggle="tooltip" data-placement="left" title="Правильный ответ" name="checkbox4">
                  <input type="text" class="form-control mt-1 ml-3" name="answer4" placeholder="Answer" maxlength="256" required>
                  <div class="invalid-feedback">
                    Please choose answer.
                  </div>
                </div>
              </div>

            </div>
          </div>

          <div class="row">
            <div class="col text-right mb-3">
              <a class="btn btn-secondary mr-3" href="<c:url value='/test/exit'/>" role="button">Отмена</a>

              <c:if test="${testSession.questions.size() < testSession.countQuestions - 1}">
                <button type="submit" class="btn btn-outline-primary">Дальше</button>
              </c:if>

              <c:if test="${testSession.questions.size() == testSession.countQuestions - 1}">
                <button type="submit" class="btn btn-dark">Сохранить</button>
              </c:if>

            </div>
          </div>

        </form>
      </div>

      <div class="col-1"></div>
    </div>
  </div>
</s:html>
