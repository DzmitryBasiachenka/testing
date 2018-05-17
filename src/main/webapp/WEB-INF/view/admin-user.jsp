<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<s:html title="Subject list">
<div class="container">
  <div class="row">
    <div class="col-2"></div>

    <div class="col-8 border border-secondary rounded pt-2">
      <h3 class="text-center">Учетная запись <label class="text-success">${user.login}</label></h3>
      <hr>

      <div class="form-group">
        <label for="inputLogin"><h4>Логин</h4></label>
        <input type="text" class="form-control" value=${user.login} disabled>
      </div>

      <div class="form-group">
        <label for="inputEmail"><h4>Почта</h4></label>
        <input type="email"class="form-control" value=${user.email} disabled>
      </div>

      <div class="form-group">
        <label for="inputFirstName"><h4>Имя</h4></label>
        <input type="text" class="form-control" value=${user.firstName} disabled>
      </div>

      <div class="form-group">
        <label for="inputLastName"><h4>Фамилия</h4></label>
        <input type="text" class="form-control" value=${user.lastName} disabled>
      </div>

      <div class="form-group">
        <label><h4>Права</h4></label>
        <c:forEach var="role" items="${user.roles}">

          <c:if test="${role.roleName eq 'User'}">
             <input type="text" class="form-control" value=${role.roleName} disabled>
          </c:if>

          <c:if test="${role.roleName ne 'User'}">
            <div class="row">
              <div class="col-10">
                <input type="text" class="form-control" value=${role.roleName} disabled>
              </div>
              <div class="col-2 text-center">
                <form action="<c:url value='/admin/role/delete/${role.id}'/>" method="POST">
                  <button type="submit" class="btn btn-outline-secondary btn-sm" name="login" value="${user.login}">Удалить</button>
                </form>
              </div>
            </div>
          </c:if>

          <p></p>
        </c:forEach>
      </div>

      <form action="<c:url value='/admin/role/add'/>" method="POST">
        <div class="form-group">
          <div class="row">
            <div class="col-10">
              <select class="form-control" name="roleSelect">
                <c:forEach var="role" items="${roles}">
                  <option>${role.roleName}</option>
                </c:forEach>
              </select>
            </div>
            <div class="col-2 text-center">
              <button type="submit" class="btn btn-outline-secondary btn-sm"  name="userId" value="${user.id}">Добавить</button>
            </div>
          </div>
        </div>
      </form>


      <div class="row mb-3">
        <div class="col"></div>
        <div class="col"></div>
        <div class="col"></div>
        <div class="col text-right">
          <a class="btn btn-outline-secondary pr-3 mr-2" href="<c:url value='/admin/user/list'/>" role="button">Отмена</a>
        </div>
      </div>

    </div>
    <div class="col-2"></div>
  </div>
</div>
</s:html>
