<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="modal fade" id="testAdd" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">

      <div class="modal-header">
        <h4>Новый тест</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>

      <div class="container">
        <form action="<c:url value='/test/add'/>" method="POST">
          <div class="modal-body">
            <div class="form-group row">
              <label for="inputTestName" class="col-4 col-form-label">Имя теста</label>
              <div class="col-8">
                <input type="text" name="testName" class="form-control" id="inputTestName" placeholder="Test name" maxlength="64" required>
                <div class="invalid-feedback">
                  Please choose test name.
                </div>
              </div>
            </div>
            <div class="form-group row">
              <label class="col-4 col-form-label">Предмет</label>
              <div class="col-8">
                <select class="form-control" name="subjectSelect">
                  <c:forEach var="subject" items="${subjects}">
                    <option>${subject.subjectName}</option>
                  </c:forEach>
                </select>
                <a class="badge badge-light" href="<c:url value='/subject'/>">Новый предмет</a>
              </div>
            </div>
            <div class="form-group row">
              <label for="inputCountQuestions" class="col-4 col-form-label">Количество вопросов</label>
              <div class="col-8">
                <input type="text" name="countQuestions" class="form-control" id="inputCountQuestions" placeholder="Count questions" required>
                <small class="form-text text-muted">от 1 до 100</small>
                <div class="invalid-feedback">
                  Please choose count of qoestions.
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
