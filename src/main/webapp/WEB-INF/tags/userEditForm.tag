<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="modal fade" id="userEdit" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="text-success">${userSession.login}</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="container">
        <form action="<c:url value='/user/edit'/>" method="POST">
          <div class="modal-body">
            <h4 class="text-center">Редактировать данные</h4>
            <hr>
            <div class="form-group">
              <label for="inputLogin"><h4>Логин</h4></label>
              <input type="text" name="login" class="form-control" id="inputLogin" value=${userSession.login} readonly>
            </div>
            <div class="form-group">
              <label for="inputEmail"><h4>Почта</h4></label>
              <input type="email" name="email" class="form-control" id="inputEmail" value=${userSession.email} required>
            </div>
            <div class="form-group">
              <label for="inputFirstName"><h4>Имя</h4></label>
              <input type="text" name="firstName" class="form-control" id="inputFirstName" value=${userSession.firstName} required>
            </div>
            <div class="form-group">
              <label for="inputLastName"><h4>Фамилия</h4></label>
              <input type="text" name="lastName" class="form-control" id="inputLastName" value=${userSession.lastName} required>
            </div>
            <div class="form-group">
              <label for="inputPassword"><h4>Новый пароль</h4></label>
              <input type="password" name="newPassword" class="form-control" id="inputPassword">
            </div>
            <div class="form-group">
              <label for="inputPassword"><h4>Подтверждение пароля</h4></label>
              <input type="password" name="confirmPassword" class="form-control" id="inputPassword">
            </div>
            <div class="form-group">
              <label for="inputLastName"><h4>Права</h4></label>
              <c:forEach var="role" items="${userSession.roles}">
                <input type="text" name="lastName" class="form-control" id="inputLastName" value=${role.roleName} readonly>
                <p></p>
              </c:forEach>
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
