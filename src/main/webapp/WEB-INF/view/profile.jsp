<%@ page language="java"
contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" scope="request" value="Profile" />
<jsp:include page="/WEB-INF/tiles/header.jsp" />
  <div class="text-center">
    <c:if test="${wrong != null}">
      <h5>${wrong}</h5>
    </c:if>
    <c:if test="${save != null}">
      <h5>${save}</h5>
    </c:if>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-2"></div>
      <div class="col-8 border border-secondary">
        <form class="mt-3">
          <h3 class="text-center">Учетная запись <label class="text-success">${userSession.getLogin()}</label></h3></label>
          <hr>
          <div class="form-group">
            <label for="inputLogin"><h4>Логин</h4></label>
            <input type="text" class="form-control" id="inputLogin" value=${userSession.getLogin()} disabled>
          </div>
          <div class="form-group">
            <label for="inputFirstName"><h4>Имя</h4></label>
            <input type="text" class="form-control" id="inputFirstName" value=${userSession.getFirstName()} disabled>
          </div>
          <div class="form-group">
            <label for="inputLastName"><h4>Фамилия</h4></label>
            <input type="text" class="form-control" id="inputLastName" value=${userSession.getLastName()} disabled>
          </div>
          <div class="form-group">
            <label for="inputRole"><h4>Права</h4></label>
            <input type="text" class="form-control" id="inputRole" value=${userSession.getRole()} disabled>
          </div>
        </form>
        <div class="row mb-3">
          <div class="col"></div>
          <div class="col"></div>
          <div class="col"></div>
          <div class="col text-right">
            <form action="<c:url value='/user/delete'/>" method="GET">
              <button type="submit" class="btn btn-secondary">Удалить</button>
            </form>
          </div>
          <div class="col text-right">
            <button class="btn btn-dark" type="button" data-toggle="modal" data-target="#userEdit" data-whatever="@mdo">Редактировать</button>
          </div>
        </div>
      <div class="col-2"></div>
    </div>
  </div>
<jsp:include page="/WEB-INF/tiles/footer.jsp" />
