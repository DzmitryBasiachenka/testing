<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${userSession == null}">
<nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <a class="navbar-brand">
    <img src="<c:url value='/pictures/logo.png'/>" width="40" height="40" alt="">
  </a>
  <div class="collapse navbar-collapse" id="navbarToggler">
      <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
        <li class="nav-item"><a class="nav-link"  href="/testing/">Главная</a></li>
        <li class="nav-item"><a class="nav-link"  href="/testing/about">О ресурсе</a></li>
      </ul>
      <a class="btn btn-outline-secondary mr-sm-2" href="<c:url value='/registration'/>" role="button">Регистрация</a>
      <button class="btn btn-outline-secondary" type="button" data-toggle="modal" data-target="#login" data-whatever="@mdo">Войти</button>
  </div>
</nav>
</c:if>

<c:if test="${userSession != null}">
  <nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand">
      <img src="<c:url value='/pictures/logo.png'/>" width="40" height="40" alt="">
    </a>
    <div class="collapse navbar-collapse" id="navbarToggler">
      <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
        <li class="nav-item"><a class="nav-link"  href="/testing/">Главная</a></li>

        <c:set var="tutor" scope="request" value="Tutor" />
        <c:forEach var="role" items="${userSession.roles}">
          <c:if test="${role.roleName eq tutor}">
            <li class="nav-item"><a class="nav-link"  href="/testing/test/list">Тест</a></li>
            <li class="nav-item"><a class="nav-link"  href="/testing/subject">Предмет</a></li>
          </c:if>
        </c:forEach>

        <c:set var="student" scope="request" value="Student" />
        <c:forEach var="role" items="${userSession.roles}">
          <c:if test="${role.roleName eq student}">
            <li class="nav-item"><a class="nav-link"  href="/testing/examination">Пройти тест</a></li>
            <li class="nav-item"><a class="nav-link"  href="/testing/statistics/list">Статистика</a></li>
          </c:if>
        </c:forEach>

        <li class="nav-item"><a class="nav-link"  href="/testing/about">О ресурсе</a></li>
      </ul>
      <a class="btn btn-outline-success mr-sm-2" href="<c:url value='/profile'/>" role="button">Профиль ${userSession.getLogin()}</a>
      <form action="<c:url value='/logout'/>" method="GET">
        <button type="submit" class="btn btn-outline-secondary">Выйти</button>
      </form>
    </div>
  </nav>
</c:if>
