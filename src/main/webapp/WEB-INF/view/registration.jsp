<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<s:html title="Registration">
<div class="container">
  <div class="row">
    <div class="col-2"></div>

    <div class="col-8 border border-secondary bg-light mb-2">
      <form action="<c:url value='/user/add'/>" method="POST" class="needs-validation mt-3" novalidate>
        <h3 class="text-center">Регистрация</h3></label>
        <hr>
        <div class="text-center">

          <c:if test="${saveUser != null}">
            <h5 class="text-success">${saveUser}</h5>
          </c:if>

          <c:if test="${loginExists != null}">
            <h5 class="text-danger">${loginExists}</h5>
          </c:if>

          <c:if test="${emailExists != null}">
            <h5 class="text-danger">${emailExists}</h5>
          </c:if>

          <c:if test="${emailWrong != null}">
            <h5 class="text-danger">${emailWrong}</h5>
          </c:if>

          <c:if test="${passwordsNotEquals != null}">
            <h5 class="text-danger">${passwordsNotEquals}</h5>
          </c:if>

          <c:if test="${emptyUser != null}">
            <h5 class="text-danger">${emptyUser}</h5>
          </c:if>

        </div>

          <div class="form-group">
            <label for="validationLogin"><h4>Логин</h4></label>
            <input type="text" name="login" class="form-control" id="validationLogin" maxlength="32" required>
            <div class="invalid-feedback">
              Please choose a login.
            </div>
          </div>

          <div class="form-group">
            <label for="validationLogin"><h4>Почта</h4></label>
            <input type="email" name="email" class="form-control" id="validationEmail" maxlength="32" required>
            <div class="invalid-feedback">
              For example: root@gmail.com
            </div>
          </div>

          <div class="form-group">
            <label for="validationFirstName"><h4>Имя</h4></label>
            <input type="text" name="firstName" class="form-control" id="validationFirstName" maxlength="64" required>
            <div class="invalid-feedback">
              Please choose first name.
            </div>
          </div>

          <div class="form-group">
            <label for="validationLastName"><h4>Фамилия</h4></label>
            <input type="text" name="lastName" class="form-control" id="validationLastName" maxlength="64" required>
            <div class="invalid-feedback">
              Please choose last name.
            </div>
          </div>

          <div class="form-group">
            <label for="validationPassword"><h4>Пароль</h4></label>
            <input type="password" name="password" class="form-control" id="validationPassword" maxlength="128" required>
            <div class="invalid-feedback">
              Please choose password.
            </div>
          </div>

          <div class="form-group">
            <label for="validationConfirmPassword"><h4>Подтверждение пароля</h4></label>
            <input type="password" name="confirmPassword" class="form-control" id="validationConfirmPassword" maxlength="128" required>
            <div class="invalid-feedback">
              Please choose confirm password.
            </div>
          </div>

          <div class="form-group">
            <label for="validationRole"><h4>Права</h4></label>
            <input type="text" class="form-control" id="validationRole" value="User" readonly>
          </div>

          <div class="text-right mb-3">
            <a class="btn btn-secondary mr-sm-3" href="<c:url value='/'/>" role="button">Отмена</a>
            <button class="btn btn-dark" type="submit">Сохранить</button>
          </div>

        </form>
        </div>
      <div class="col-2"></div>
    </div>
  </div>
</s:html>
