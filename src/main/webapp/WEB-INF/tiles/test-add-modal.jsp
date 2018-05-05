<%@ page language="java"
contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
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
                <input type="text" name="testName" class="form-control" id="inputTestName" placeholder="Test name">
              </div>
            </div>
            <div class="form-group row">
              <label for="inputCountQuestions" class="col-4 col-form-label">Количество вопросов</label>
              <div class="col-8">
                <input type="text" name="countQuestions" class="form-control" id="inputCountQuestions" placeholder="Count questions">
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
            <button class="btn btn-dark" type="button" data-toggle="modal" data-target="#testSubjectAdd" data-whatever="@mdo">Дальше</button>
            <!--<button type="submit" class="btn btn-dark">Дальше</button>-->
          </div>
        </form>
      </div>
    </div>
  </div>
</div>