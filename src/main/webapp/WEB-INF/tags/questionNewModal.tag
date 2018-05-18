<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="modal fade" id="newQuestion" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">

      <div class="modal-header">
        <h4>Новый вопрос</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>

      <div class="container">
        <form action="<c:url value='/question/new/${test.id}'/>" method="POST">
          <div class="modal-body">
            <div class="form-group row">
              <label for="inputTestName" class="col-4 col-form-label">Вопрос</label>
              <div class="col-12">
                <input type="text" name="questionName" class="form-control" placeholder="Question" required>
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
          </div>

          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
            <button type="submit" class="btn btn-dark">Сохранить</button>
          </div>
        </form>
      </div>

    </div>
  </div>
</div>
