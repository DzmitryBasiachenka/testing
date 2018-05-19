<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.question" var="question"/>
<fmt:message key="t.new.question" var="newQuestion"/>
<fmt:message key="t.question.placeholder" var="questionPlaceholder"/>
<fmt:message key="t.correct.answer" var="correctAnswer"/>
<fmt:message key="t.answer.placeholder" var="answerPlaceholder"/>
<fmt:message key="t.close" var="close"/>
<fmt:message key="t.save" var="save"/>

<div class="modal fade" id="newQuestion" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">

      <div class="modal-header">
        <h4>${newQuestion}</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>

      <div class="container">
        <form action="<c:url value='/question/new/${test.id}'/>" method="POST">
          <div class="modal-body">
            <div class="form-group row">
              <label for="inputTestName" class="col-4 col-form-label">${question}</label>
              <div class="col-12">
                <input type="text" name="questionName" class="form-control" placeholder="${questionPlaceholder}" required>

                <div class="col-12">
                  <div class="form-check">
                    <input type="checkbox" class="form-check-input mt-2" data-toggle="tooltip" data-placement="left" title="${correctAnswer}" name="checkbox1">
                    <input type="text" class="form-control mt-2 ml-3" name="answer1" placeholder="${answerPlaceholder}" maxlength="256" required>
                  </div>

                  <div class="form-check">
                    <input type="checkbox" class="form-check-input mt-2" data-toggle="tooltip" data-placement="left" title="${correctAnswer}" name="checkbox2">
                    <input type="text" class="form-control mt-1 ml-3" name="answer2" placeholder="${answerPlaceholder}" maxlength="256" required>
                  </div>

                  <div class="form-check">
                    <input type="checkbox" class="form-check-input mt-2" data-toggle="tooltip" data-placement="left" title="${correctAnswer}" name="checkbox3">
                    <input type="text" class="form-control mt-1 ml-3" name="answer3" placeholder="${answerPlaceholder}" maxlength="256" required>
                  </div>

                  <div class="form-check">
                    <input type="checkbox"  class="form-check-input mt-2" data-toggle="tooltip" data-placement="left" title="${correctAnswer}" name="checkbox4">
                    <input type="text" class="form-control mt-1 ml-3" name="answer4" placeholder="${answerPlaceholder}" maxlength="256" required>
                  </div>

                </div>

              </div>
            </div>
          </div>

          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">${close}</button>
            <button type="submit" class="btn btn-dark">${save}</button>
          </div>
        </form>
      </div>

    </div>
  </div>
</div>
